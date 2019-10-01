package ru.pushapptest.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.pushapptest.todolist.database.models.TodoDB;
import ru.pushapptest.todolist.models.Todo;
import ru.pushapptest.todolist.ui.ToDoFragment;
import ru.pushapptest.todolist.ui.ToDoListFragment;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Todo> toDoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //generateList();
    }

    @Override
    protected void onStart(){
        super.onStart();
//        App.getDb().todoDao().getAll()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .flatMapCompletable(todoDb -> Observable.fromIterable(todoDb)
//                        .map(item -> Todo.toDoDbtoTodo(item))
//                        .toList()
//                        .doOnSuccess(todos -> {
//                            toDoList =  new ArrayList<>(todos);
//                        })
//                        .ignoreElement()
//                )
//                .subscribe();
        startToDoListFragment();
    }

    @Override
    protected void onStop(){
        super.onStop();
//        toDotoTodoDb();
//        Completable.mergeArray(
//                App.getDb().todoDao().deleteAll()
//        ).subscribeOn(Schedulers.io())
//                .andThen(Completable.mergeArray(
//                        App.getDb().todoDao().insertAll(toDotoTodoDb())
//                ))
//                .subscribe();
    }

    private List<TodoDB> toDotoTodoDb() {
        List <TodoDB> todoDBS= new ArrayList<>();
        for (int i = 0; i < toDoList.size(); i++ ){
            todoDBS.add(new TodoDB(i, toDoList.get(i).headText, toDoList.get(i).mainText, toDoList.get(i).status));
        }
        return  todoDBS;
    }

    private void generateList() {
        for (int i = 0; i < 10; i++) {
            toDoList.add(new Todo("Head" + i, "Some text", "GREEN"));
        }
        toDoList.add(new Todo("Head10", "Родион, да Джеффа и так задолбали с просьбами сделать лор на Дзена , поэтому есть огромный шанс того , что короткометражка будет именно про него , + на интервью у Сигала он говорил , что сейчас (на момент августа ) они практически постоянно готовятся к близкону , поэтому ждём", "GREEN"));
    }

    private void startToDoListFragment() {
        Fragment toDoListFragment = new ToDoListFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.activity_main, toDoListFragment).commit();
    }
}
