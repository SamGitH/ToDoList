package ru.pushapptest.todolist.ui;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.pushapptest.todolist.R;
import ru.pushapptest.todolist.models.Todo;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder>{

    private List<Todo> todos;
    private final Listener onTodoClickListener;

    public TodoAdapter(List<Todo> todos, Listener onTodoClickListener) {
        this.todos = todos;
        this.onTodoClickListener = onTodoClickListener;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_todo, viewGroup, false);
        return new TodoViewHolder(view, onTodoClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder viewHolder, int i) {
        Todo todo = todos.get(i);
        viewHolder.bind(todo, i);
    }

    public interface Listener{
        void onTodoClicked(Todo todo, int number);
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    static final class TodoViewHolder extends RecyclerView.ViewHolder {

        private final TextView headText;
        private final TextView mainText;
        private final View status;
        private Todo todo;
        private int number;

        public TodoViewHolder(@NonNull View itemView, final Listener onTodoClickListener) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onTodoClickListener.onTodoClicked(todo, number);
                }
            });
            headText = itemView.findViewById(R.id.it_text_view_head);
            mainText = itemView.findViewById(R.id.it_text_view_main);
            status = itemView.findViewById(R.id.it_status);
        }

        @SuppressLint("SetTextI18n")
        private void bind(@NonNull Todo todo, int number) {
            this.todo = todo;
            this.number = number;
            headText.setText(todo.headText);
            mainText.setText(todo.mainText);
            if(todo.status != null) {
                if (todo.status.equals("GREEN"))
                    status.setBackgroundColor(status.getContext().getResources().getColor(R.color.colorStatusGreen));
                if (todo.status.equals("YELLOW"))
                    status.setBackgroundColor(status.getContext().getResources().getColor(R.color.colorStatusYellow));
                if (todo.status.equals("RED"))
                    status.setBackgroundColor(status.getContext().getResources().getColor(R.color.colorStatusRed));
            }
        }

    }
}
