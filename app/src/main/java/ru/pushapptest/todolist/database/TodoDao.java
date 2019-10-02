package ru.pushapptest.todolist.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import ru.pushapptest.todolist.database.models.TodoDB;
import ru.pushapptest.todolist.models.Todo;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface TodoDao {

    @Query("SELECT * FROM tododb")
    Single<List<TodoDB>> getAll();

    @Query("DELETE FROM tododb")
    Completable deleteAll();

    @Insert(onConflict = REPLACE)
    Completable insertAll(List<TodoDB> todos);

    @Insert(onConflict = REPLACE)
    Single<Long> insertOrUdate(TodoDB todo);

    @Delete
    Completable delete(TodoDB report);
}