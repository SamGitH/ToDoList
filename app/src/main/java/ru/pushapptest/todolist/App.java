package ru.pushapptest.todolist;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.facebook.stetho.Stetho;

import ru.pushapptest.todolist.database.AppDataBase;

public class App extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

        Stetho.initializeWithDefaults(this);
    }

    public static Context getContext(){
        return context;
    }
}