package ru.pushapptest.todolist.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.pushapptest.todolist.R;
import ru.pushapptest.todolist.models.Todo;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder>{

    private List<Todo> todos;
    private final Listener onTodoClickListener;
    private Context context;

    public TodoAdapter(List<Todo> todos, Context context,Listener onTodoClickListener) {
        this.todos = todos;
        this.onTodoClickListener = onTodoClickListener;
        this.context = context;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_todo, viewGroup, false);
        return new TodoViewHolder(view, context, onTodoClickListener);
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
        private Context context;

        public TodoViewHolder(@NonNull View itemView, Context context,final Listener onTodoClickListener) {
            super(itemView);
            this.context = context;
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
//            switch (todo.status){
//                case GREEN:
//                    status.setBackgroundColor(context.getResources()
//                            .getColor(R.color.colorStatusGreen));
//                    break;
//                case YELLOW:
//                    status.setBackgroundColor(context.getResources()
//                            .getColor(R.color.colorStatusYellow));
//                    break;
//                case RED:
//                    status.setBackgroundColor(context.getResources()
//                            .getColor(R.color.colorStatusRed));
//                    break;
//                default:
//                    break;
//            }
        }

    }
}
