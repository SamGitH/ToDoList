package ru.pushapptest.todolist.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import ru.pushapptest.todolist.MainActivity;
import ru.pushapptest.todolist.R;
import ru.pushapptest.todolist.data.database.TodoRepository;
import ru.pushapptest.todolist.models.Todo;

public class TodoFragment extends Fragment implements View.OnClickListener {
    public static final String TODO = "TODO";

    private Todo todo;
    private int number;
    private String status;

    private final int BUT_BACK = R.id.ft_back_but;
    private final int BUT_CREATE = R.id.ft_btn_create;
    private final int BUT_RED = R.id.ft_circle_red;
    private final int BUT_YELLOW = R.id.ft_circle_yellow;
    private final int BUT_GREEN = R.id.ft_circle_green;
    private final int BUT_DONE = R.id.ft_btn_done;

    private EditText headText;
    private EditText mainText;
    private ImageButton backBtn;
    private Button createBtn;
    private Button redBtn;
    private Button yellowBtn;
    private Button greenBtn;
    private Button doneBtn;

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

            loadTodo(number);
        }

        bindViews(view);
    }

    private void loadTodo(int id) {
        Disposable disposable = TodoRepository.getInstance().getTodos().flatMapObservable(Observable::fromIterable)
                .filter(todo -> todo.id == id)
                .firstOrError()
                .subscribe(this::setupTodo);
    }

    private void setupTodo(Todo todo) {
        this.todo = todo;
        bind();
    }

    private void bindViews(View view) {
        headText = view.findViewById(R.id.ft_edit_text_head);
        mainText = view.findViewById(R.id.ft_edit_text_main);
        backBtn = view.findViewById(BUT_BACK);

        createBtn = view.findViewById(BUT_CREATE);
        redBtn = view.findViewById(BUT_RED);
        yellowBtn = view.findViewById(BUT_YELLOW);
        greenBtn = view.findViewById(BUT_GREEN);
        doneBtn = view.findViewById(BUT_DONE);
    }

    private void bind() {
        if (todo != null) {
            headText.setText(todo.headText);
            mainText.setText(todo.mainText);
            createBtn.setText("ИЗМЕНИТЬ");
            doneBtn.setVisibility(View.VISIBLE);
            doneBtn.setOnClickListener(this);
            if (todo.status != null) {
                if (todo.status.equals("GREEN"))
                    greenBtn.setBackground(getContext().getResources().getDrawable(R.drawable.check_circle_green));
                if (todo.status.equals("YELLOW"))
                    yellowBtn.setBackground(getContext().getResources().getDrawable(R.drawable.check_circle_yellow));
                if (todo.status.equals("RED"))
                    redBtn.setBackground(getContext().getResources().getDrawable(R.drawable.check_circle_red));
            }
            //сделать статус
        } else
            doneBtn.setVisibility(View.INVISIBLE);
        backBtn.setOnClickListener(this);
        createBtn.setOnClickListener(this);
        redBtn.setOnClickListener(this);
        yellowBtn.setOnClickListener(this);
        greenBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == BUT_BACK) {
            Fragment toDoListFragment = new ToDoListFragment();
            if (getFragmentManager() != null) {
                getFragmentManager().beginTransaction().replace(R.id.activity_main, toDoListFragment).commit();
            }
        }
        if (v.getId() == BUT_CREATE) {
            if (todo != null) {
                todo.headText = headText.getText().toString();
                todo.mainText = mainText.getText().toString();
                todo.status = status;
                TodoRepository.getInstance().remove(number);
                TodoRepository.getInstance().add(number, todo);
            } else {
                TodoRepository.getInstance().add(new Todo(headText.getText().toString(),
                        mainText.getText().toString(),
                        status));
                Fragment toDoListFragment = new ToDoListFragment();
                if (getFragmentManager() != null) {
                    getFragmentManager().beginTransaction().replace(R.id.activity_main, toDoListFragment).commit();
                }
                MainActivity.sortList();
            }
            if (v.getId() == BUT_DONE) {
                MainActivity.toDoList.remove(number);
                Fragment toDoListFragment = new ToDoListFragment();
                if (getFragmentManager() != null) {
                    getFragmentManager().beginTransaction().replace(R.id.activity_main, toDoListFragment).commit();
                }
                MainActivity.sortList();
            }
            if (v.getId() == BUT_RED) {
                status = "RED";
                redBtn.setBackground(v.getContext().getResources().getDrawable(R.drawable.check_circle_red));
                yellowBtn.setBackground(v.getContext().getResources().getDrawable(R.drawable.circle_yellow));
                greenBtn.setBackground(v.getContext().getResources().getDrawable(R.drawable.circle_green));
            }
            if (v.getId() == BUT_YELLOW) {
                status = "YELLOW";
                redBtn.setBackground(v.getContext().getResources().getDrawable(R.drawable.circle_red));
                yellowBtn.setBackground(v.getContext().getResources().getDrawable(R.drawable.check_circle_yellow));
                greenBtn.setBackground(v.getContext().getResources().getDrawable(R.drawable.circle_green));
            }
            if (v.getId() == BUT_GREEN) {
                status = "GREEN";
                redBtn.setBackground(v.getContext().getResources().getDrawable(R.drawable.circle_red));
                yellowBtn.setBackground(v.getContext().getResources().getDrawable(R.drawable.circle_yellow));
                greenBtn.setBackground(v.getContext().getResources().getDrawable(R.drawable.check_circle_green));
            }
        }

        void onBackBtnClicked () {
            //logic
        }

    }