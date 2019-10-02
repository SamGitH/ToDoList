package ru.pushapptest.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ru.pushapptest.todolist.data.database.TodoRepository;
import ru.pushapptest.todolist.data.database.models.TodoDB;
import ru.pushapptest.todolist.models.Todo;
import ru.pushapptest.todolist.ui.ToDoListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        setContentView(R.layout.activity_main);
        //generateList();
        startToDoListFragment();
    }

//    private void toDoDBtoTodo(List<TodoDB> todoDB) {
//        for (int i = 0; i < todoDB.size(); i++ ){
//            toDoList.add(new Todo(todoDB.get(i).headText, todoDB.get(i).mainText, todoDB.get(i).status));
//        }
//    }
//
//    private void generateList() {
//        for (int i = 0; i < 10; i++) {
//            toDoList.add(new Todo("Head" + i, "Some text", "GREEN"));
//        }
//        toDoList.add(new Todo("Aead10", "Родион, да Джеффа и так задолбали с просьбами сделать лор на Дзена , поэтому есть огромный шанс того , что короткометражка будет именно про него , + на интервью у Сигала он говорил , что сейчас (на момент августа ) они практически постоянно готовятся к близкону , поэтому ждём", "GREEN"));
//    }

    private void startToDoListFragment() {
        Fragment toDoListFragment = new ToDoListFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.activity_main, toDoListFragment).commit();
    }
}
