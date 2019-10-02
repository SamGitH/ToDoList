package ru.pushapptest.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import ru.pushapptest.todolist.database.WorkDB;
import ru.pushapptest.todolist.database.models.TodoDB;
import ru.pushapptest.todolist.models.Todo;
import ru.pushapptest.todolist.ui.ToDoFragment;
import ru.pushapptest.todolist.ui.ToDoListFragment;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Todo> toDoList = new ArrayList<>();
    private static WorkDB workDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        setContentView(R.layout.activity_main);
        workDB = WorkDB.getWorkDB(this);
        //generateList();
    }

    @Override
    protected void onStart(){
        super.onStart();
        WorkDB.loadData();
//        Completable.mergeArray(
//                toDoDBtoTodo(App.getDb().todoDao().getAll())
//        ).subscribeOn(Schedulers.io())
//                .subscribe();
//        App.getDb().todoDao().getAll()
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<List<TodoDB>>() {
//                    @Override
//                    public void accept(List<TodoDB> todoDBS) throws Exception {
//                        toDoDBtoTodo(todoDBS);                    }
//                });
        sortList();
        startToDoListFragment();
    }

    @Override
    protected void onStop(){
        super.onStop();
        WorkDB.unloadData();
    }

    private void toDoDBtoTodo(List<TodoDB> todoDB) {
        for (int i = 0; i < todoDB.size(); i++ ){
            toDoList.add(new Todo(todoDB.get(i).headText, todoDB.get(i).mainText, todoDB.get(i).status));
        }
    }

    private void generateList() {
        for (int i = 0; i < 10; i++) {
            toDoList.add(new Todo("Head" + i, "Some text", "GREEN"));
        }
        toDoList.add(new Todo("Aead10", "Родион, да Джеффа и так задолбали с просьбами сделать лор на Дзена , поэтому есть огромный шанс того , что короткометражка будет именно про него , + на интервью у Сигала он говорил , что сейчас (на момент августа ) они практически постоянно готовятся к близкону , поэтому ждём", "GREEN"));
    }

    public static void sortList(){
        Collections.sort(toDoList, new Comparator<Todo>() {
            @Override
            public int compare(Todo o1, Todo o2) {
                return o1.headText.compareTo(o2.headText);
            }
        });
    }

    private void startToDoListFragment() {
        Fragment toDoListFragment = new ToDoListFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.activity_main, toDoListFragment).commit();
    }
}
