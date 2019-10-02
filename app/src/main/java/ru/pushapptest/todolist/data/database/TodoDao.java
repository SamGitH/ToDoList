package ru.pushapptest.todolist.data.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import ru.pushapptest.todolist.data.database.models.TodoDB;

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
    Completable insert(TodoDB todo);
}
