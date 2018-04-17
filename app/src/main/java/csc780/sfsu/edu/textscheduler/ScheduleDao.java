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
public interface ScheduleDao {
    @Query("SELECT * FROM schedule")
    List<Schedule> getAll();

    @Query("SELECT * FROM schedule WHERE id IN (:scheduleIds)")
    List<Schedule> loadAllByIds(int[] scheduleIds);

    @Query("SELECT * FROM schedule WHERE text_id = :textId LIMIT 1")
    Schedule findByTextId(int textId);

    @Query("SELECT * FROM schedule WHERE id = :id LIMIT 1")
    Schedule findById(int id);

//    @Query("SELECT * FROM text WHERE id = :textId LIMIT 1")
//    Text findTextById(int textId);

    @Insert
    void insertAll(Schedule... schedules);

    @Delete
    void delete(Schedule schedule);
}
