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

import java.util.ArrayList;
import java.util.Collections;

import io.reactivex.disposables.Disposable;
import ru.pushapptest.todolist.R;
import ru.pushapptest.todolist.data.database.TodoRepository;
import ru.pushapptest.todolist.models.Todo;

public class ToDoListFragment extends Fragment {

    Disposable loadTodosDisposable;

    private final TodoAdapter todoAdapter = new TodoAdapter(new ArrayList<>(), new TodoAdapter.Listener() {
        @Override
        public void onTodoClicked(Todo todo, int number) {
            Bundle arg = new Bundle();
            Fragment toDoFragment = new TodoFragment();
//            arg.putParcelable(TodoFragment.TODO, todo);
            arg.putInt(TodoFragment.TODO, number);
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
    public void onDestroyView() {
        super.onDestroyView();

        if (loadTodosDisposable != null) {
            loadTodosDisposable.dispose();
            loadTodosDisposable = null;
        }
    }

    @Override
    public void onViewCreated(@Nullable View view, @Nullable Bundle savedInstanceState) {
        if (view != null) {
            findViews(view);
        }
        bind();

        loadData();
    }

    private void loadData() {
        loadTodosDisposable = TodoRepository.getInstance().getTodos()
                .map(list -> {
                    Collections.sort(list, (o1, o2) -> o1.headText.compareTo(o2.headText));
                    return list;
                })
                .doOnSuccess((todos) -> {
                    todoAdapter.setTodos(todos);
                    todoAdapter.notifyDataSetChanged();
                }).subscribe();
    }

    private void findViews(@NonNull View view) {
        recyclerView = view.findViewById(R.id.fl_recyclerview);
        fab = view.findViewById(R.id.fl_fab);
    }

    private void bind() {
        recyclerView.setAdapter(todoAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment toDoFragment = new TodoFragment();
                if (getFragmentManager() != null) {
                    getFragmentManager().beginTransaction().replace(R.id.activity_main, toDoFragment).commit();
                }
            }
        });
    }
}
