package ru.pushapptest.todolist.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

import ru.pushapptest.todolist.MainActivity;
import ru.pushapptest.todolist.R;
import ru.pushapptest.todolist.models.Todo;

public class ToDoFragment extends Fragment implements View.OnClickListener {
    public static final String TODO = "TODO";

    private Todo todo;
    private int number;
    private String status;

    private final int BUT_BACK = R.id.ft_back_but;
    private final int BUT_CREATE = R.id.ft_btn_create;
    private final int BUT_RED = R.id.ft_circle_red;
    private final int BUT_YELLOW = R.id.ft_circle_yellow;
    private final int BUT_GREEN = R.id.ft_circle_green;

    private EditText headText;
    private EditText mainText;
    private Button backBtn;
    private Button createBtn;
    private Button redBtn;
    private Button yellowBtn;
    private Button greenBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_todo, container, false);
    }

    @Override
    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {

        Bundle args = getArguments();
        if (args != null) {
            number = args.getInt(TODO);
            todo = MainActivity.toDoList.get(number);
        }

        findViews(view);
        bind();
    }

    private void findViews(View view){
        headText = view.findViewById(R.id.ft_edit_text_head);
        mainText = view.findViewById(R.id.ft_edit_text_main);
        backBtn = view.findViewById(BUT_BACK);
        createBtn = view.findViewById(BUT_CREATE);
        redBtn = view.findViewById(BUT_RED);
        yellowBtn = view.findViewById(BUT_YELLOW);
        greenBtn = view.findViewById(BUT_GREEN);
    }

    private void bind() {
        if(todo != null){
            headText.setText(todo.headText);
            mainText.setText(todo.mainText);
            createBtn.setText("ИЗМЕНИТЬ");
            //сделать статус
        }
        backBtn.setOnClickListener(this);
        createBtn.setOnClickListener(this);
        redBtn.setOnClickListener(this);
        yellowBtn.setOnClickListener(this);
        greenBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == BUT_BACK) {
            Fragment toDoListFragment = new ToDoListFragment();
            if (getFragmentManager() != null) {
                getFragmentManager().beginTransaction().replace(R.id.activity_main, toDoListFragment).commit();
            }
        }
        if(v.getId() == BUT_CREATE) {
            if(todo != null) {
                todo.headText = headText.getText().toString();
                todo.mainText = mainText.getText().toString();
                todo.status = status;
                MainActivity.toDoList.remove(number);
                MainActivity.toDoList.add(number, todo);
            }
            else
                MainActivity.toDoList.add(new Todo(headText.getText().toString(),
                        mainText.getText().toString(),
                        status));
            Fragment toDoListFragment = new ToDoListFragment();
            if (getFragmentManager() != null) {
                getFragmentManager().beginTransaction().replace(R.id.activity_main, toDoListFragment).commit();
            }
        }
        if(v.getId() == BUT_RED){
            //сделать статус
            status = "RED";
        }
        if(v.getId() == BUT_YELLOW){
            status = "YELLOW";
        }
        if(v.getId() == BUT_GREEN){
            status = "GREEN";
        }
    }
}
