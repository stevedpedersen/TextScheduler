package csc780.sfsu.edu.textscheduler.model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by sp on 4/16/18.
 */
@Dao
public interface ScheduleDao {
    @Query("SELECT * FROM schedules")
    LiveData<List<Schedule>> getAll();

    @Query("SELECT * FROM schedules WHERE id IN (:scheduleIds)")
    List<Schedule> loadAllByIds(int[] scheduleIds);

    @Query("SELECT * FROM schedules WHERE text_id = :textId LIMIT 1")
    Schedule findByTextId(int textId);

    @Query("SELECT * FROM schedules WHERE id = :id LIMIT 1")
    Schedule findById(int id);

//    @Query("SELECT * FROM text WHERE id = :textId LIMIT 1")
//    Text findTextById(int textId);

    @Insert
    void insertAll(Schedule... schedules);

    @Insert
    void insert(Schedule schedule);

    @Delete
    void delete(Schedule schedule);
}
