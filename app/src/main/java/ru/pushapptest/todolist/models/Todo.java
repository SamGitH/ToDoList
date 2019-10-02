package ru.pushapptest.todolist.models;

import ru.pushapptest.todolist.database.models.TodoDB;

public class Todo {
    public String headText;
    public String mainText;
    public String status;

    public Todo(String headText, String mainText, String status) {
        this.headText = headText;
        this.mainText = mainText;
        this.status = status;
    }

    public static Todo toDoDbtoTodo(TodoDB todoDB){
        return new Todo(todoDB.headText, todoDB.mainText, todoDB.status);
    }
}
