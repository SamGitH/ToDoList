package ru.pushapptest.todolist.database.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TodoDB {

    @PrimaryKey
    public final long id;
    public final String headText;
    public final String mainText;
    public final String status;

    public TodoDB(long id, String headText, String mainText, String status) {
        this.id = id;
        this.headText = headText;
        this.mainText = mainText;
        this.status = status;
    }
}