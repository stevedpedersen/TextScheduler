package csc780.sfsu.edu.textscheduler;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by sp on 4/16/18.
 */

@Dao
public interface TextDao {
    @Query("SELECT * FROM text")
    List<Text> getAll();

    @Query("SELECT * FROM text WHERE id IN (:textIds)")
    List<Text> loadAllByIds(int[] textIds);

    @Query("SELECT * FROM text WHERE title LIKE :title LIMIT 1")
    Text findByTitle(String title);

    @Query("SELECT * FROM text WHERE id = :id LIMIT 1")
    Text findById(int id);

    @Insert
    void insertAll(Text... texts);

    @Delete
    void delete(Text text);
}
