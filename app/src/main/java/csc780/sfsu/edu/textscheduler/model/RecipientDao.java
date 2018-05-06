package csc780.sfsu.edu.textscheduler.model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by sp on 4/17/18.
 */

@Dao
public interface RecipientDao {
    @Query("SELECT * FROM recipients")
    LiveData<List<Recipient>> getAll();

//    @Query("SELECT * FROM recipients WHERE id IN (:scheduleIds)")
//    List<Schedule> loadAllByIds(int[] scheduleIds);

    @Query("SELECT * FROM recipients WHERE text_id = :textId")
    LiveData<List<Recipient>> findByTextId(int textId);

    @Query("SELECT * FROM recipients WHERE phone LIKE :phone LIMIT 1")
    Recipient findByPhone(String phone);

//    @Query("SELECT * FROM text WHERE id = :textId LIMIT 1")
//    Text findTextById(int textId);

    @Insert
    void insertAll(Recipient... recipients);

    @Insert
    void insert(Recipient recipient);

    @Delete
    void delete(Recipient recipient);
}
