package ru.pushapptest.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import ru.pushapptest.todolist.database.WorkDB;
import ru.pushapptest.todolist.models.Todo;
import ru.pushapptest.todolist.ui.ToDoListFragment;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Todo> toDoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart(){
        super.onStart();
        WorkDB.loadData();
        startToDoListFragment();
    }

    @Override
    protected void onStop(){
        super.onStop();
        WorkDB.unloadData();
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
