package ru.pushapptest.todolist;

import android.app.Application;

import com.facebook.stetho.Stetho;

import ru.pushapptest.todolist.database.AppDataBase;

public class App extends Application {

    private static AppDataBase db;

    @Override
    public void onCreate() {
        super.onCreate();
        db = AppDataBase.getAppDatabase(this);
        Stetho.initializeWithDefaults(this);
    }

    public static AppDataBase getDb() {
        return db;
    }
}
