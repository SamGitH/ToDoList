package ru.pushapptest.todolist.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import ru.pushapptest.todolist.database.models.TodoDB;

@Database(entities = {TodoDB.class}, version = 2, exportSchema = false)

public abstract class AppDataBase extends RoomDatabase {

    private static AppDataBase singleton;
    private static final String DATABASE_NAME = "todo.db";
    public abstract TodoDao todoDao();

    public static AppDataBase getAppDatabase(Context context) {
        if (singleton == null) {
            synchronized (AppDataBase.class) {
                if (singleton == null) {
                    singleton = Room.databaseBuilder(context.getApplicationContext(),
                            AppDataBase.class,
                            DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return singleton;
    }
}