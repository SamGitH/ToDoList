package ru.pushapptest.todolist.data.database;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.pushapptest.todolist.App;
import ru.pushapptest.todolist.data.database.models.TodoDB;
import ru.pushapptest.todolist.models.Todo;

public class TodoRepository {

    private static TodoRepository instance;

    private static AppDataBase db;

    private List<Todo> todos;

    private TodoRepository() {
        db = AppDataBase.getAppDatabase(App.getContext());
    }

    public static TodoRepository getInstance() {
        if (instance == null) {
            synchronized (TodoRepository.class) {
                if (instance == null) {
                    instance = new TodoRepository();
                }
            }
        }
        return instance;
    }

    public Single<List<Todo>> getTodos() {
        if (todos != null) {
            return Single.just(todos);
        } else {
            return db.todoDao().getAll()
                    .subscribeOn(Schedulers.io())
                    .flatMapObservable(Observable::fromIterable)
                    .map(TodoRepository::toDoDbtoTodo)
                    .toList()
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSuccess(todos -> this.todos = todos);
        }
    }

    public Completable addTodo(Todo todo) {
        todos.add(todo);

//        db.todoDao().insert();

        return Completable.complete();
    }

//    public static void unloadData() {
//        Completable.mergeArray(
//                App.getDb().todoDao().deleteAll()
//        ).subscribeOn(Schedulers.io())
//                .andThen(Completable.mergeArray(
//                        App.getDb().todoDao().insertAll(toDotoTodoDb())
//                ))
//                .subscribe();
//    }

//    private static List<TodoDB> toDotoTodoDb() {
//        List<TodoDB> todoDBS = new ArrayList<>();
//        for (int i = 0; i < MainActivity.toDoList.size(); i++) {
//            todoDBS.add(new TodoDB(i, MainActivity.toDoList.get(i).headText,
//                    MainActivity.toDoList.get(i).mainText,
//                    MainActivity.toDoList.get(i).status));
//        }
//        return todoDBS;
//    }

    public static Todo toDoDbtoTodo(TodoDB todoDB) {
        return new Todo(todoDB.id, todoDB.headText, todoDB.mainText, todoDB.status);
    }
}