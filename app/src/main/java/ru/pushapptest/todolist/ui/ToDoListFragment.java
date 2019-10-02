package ru.pushapptest.todolist.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import ru.pushapptest.todolist.MainActivity;
import ru.pushapptest.todolist.R;
import ru.pushapptest.todolist.database.WorkDB;
import ru.pushapptest.todolist.models.Todo;

public class ToDoListFragment extends Fragment {

    private final TodoAdapter todoAdapter = new TodoAdapter(MainActivity.toDoList, new TodoAdapter.Listener() {
        @Override
        public void onTodoClicked(Todo todo, int number) {
            Bundle arg = new Bundle();
            Fragment toDoFragment = new ToDoFragment();
//            arg.putParcelable(ToDoFragment.TODO, todo);
            arg.putInt(ToDoFragment.TODO, number);
            toDoFragment.setArguments(arg);
            if (getFragmentManager() != null) {
                getFragmentManager().beginTransaction().replace(R.id.activity_main, toDoFragment).commit();
            }
        }
    });

    private RecyclerView recyclerView;
    private FloatingActionButton fab;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_todolist, container, false);
    }

    @Override
    public void onViewCreated(@Nullable View view, @Nullable Bundle savedInstanceState) {
        if (view != null) {
            findViews(view);
        }
        bind();
    }

    private void findViews(@NonNull View view){
        recyclerView = view.findViewById(R.id.fl_recyclerview);
        fab = view.findViewById(R.id.fl_fab);
    }

    private void bind() {
        recyclerView.setAdapter(todoAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment toDoFragment = new ToDoFragment();
                if (getFragmentManager() != null) {
                    getFragmentManager().beginTransaction().replace(R.id.activity_main, toDoFragment).commit();
                }
            }
        });
        WorkDB.todoAdapter = todoAdapter;//не робит
    }
}
