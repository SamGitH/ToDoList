package ru.pushapptest.todolist.database;

import android.content.Context;

import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.pushapptest.todolist.App;
import ru.pushapptest.todolist.MainActivity;
import ru.pushapptest.todolist.database.models.TodoDB;
import ru.pushapptest.todolist.models.Todo;
import ru.pushapptest.todolist.ui.TodoAdapter;

public class WorkDB {

    private static WorkDB singleton;
    public static Boolean isLoad = false;
    public static TodoAdapter todoAdapter;

    public static WorkDB getWorkDB(Context context) {
        if (singleton == null) {
            synchronized (WorkDB.class) {
                if (singleton == null) {
                    singleton = new WorkDB();
                }
            }
        }
        return singleton;
    }

    public static void loadData(){
        App.getDb().todoDao().getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMapCompletable(todoDb -> Observable.fromIterable(todoDb)
                        .map(item -> Todo.toDoDbtoTodo(item))
                        .toList()
                        .doOnSuccess(todos -> {
                            MainActivity.toDoList =  new ArrayList<>(todos);
                            if(todoAdapter != null)
                                todoAdapter.notifyDataSetChanged();
                        })
                        .ignoreElement()
                )
                .subscribe();
        isLoad = true;
    }

    public static void unloadData(){
        Completable.mergeArray(
                App.getDb().todoDao().deleteAll()
        ).subscribeOn(Schedulers.io())
                .andThen(Completable.mergeArray(
                        App.getDb().todoDao().insertAll(toDotoTodoDb())
                ))
                .subscribe();
    }

    private static List<TodoDB> toDotoTodoDb() {
        List <TodoDB> todoDBS = new ArrayList<>();
        for (int i = 0; i < MainActivity.toDoList.size(); i++ ){
            todoDBS.add(new TodoDB(i, MainActivity.toDoList.get(i).headText,
                    MainActivity.toDoList.get(i).mainText,
                    MainActivity.toDoList.get(i).status));
        }
        return  todoDBS;
    }
}
