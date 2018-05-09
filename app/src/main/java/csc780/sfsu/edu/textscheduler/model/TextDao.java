package csc780.sfsu.edu.textscheduler.model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by sp on 4/16/18.
 */

@Dao
public interface TextDao {
    @Query("SELECT * FROM texts")
    LiveData<List<Text>> getAll();

    @Query("SELECT * FROM texts")
    List<Text> getAllSync();

    @Query("SELECT * FROM texts WHERE id = :id LIMIT 1")
    LiveData<List<Text>> get(int id);

//    @Query("SELECT * FROM texts")
//    public Text[] getAll();

    @Query("SELECT * FROM texts WHERE id IN (:textIds)")
    List<Text> loadAllByIds(int[] textIds);

    @Query("SELECT * FROM texts WHERE title LIKE :title LIMIT 1")
    Text findByTitle(String title);

    @Query("SELECT * FROM texts WHERE id = :id LIMIT 1")
    Text findById(int id);

    // @TODO: Test date comparison
    @Query("SELECT * FROM texts WHERE created_date < :someDate")
    public Text[] loadAllTextsOlderThan(String someDate);

    @Query("SELECT * FROM texts WHERE title LIKE :search ")
//            + "OR recipient LIKE :search")
    public List<Text> findTextsByTitle(String search);

//    @Query("SELECT * FROM texts WHERE recipients LIKE :search")
//    public List<Text> findTextsByRecipient(String search);

//    @Query("SELECT recipients FROM texts t, recipients r "
//            + "WHERE t.id = r.text_id AND t.id = :id")
//    public List<Recipient> findRecipientsForText(int id);

//    // @TODO: double check this join on recipients
//    @Query("SELECT t.id, t.title, t.message, t.created_date, "
//                + "r.first_name, r.last_name, r.phone, "
//                + "s.send_date, s.sent "
//            + "FROM texts t, schedules s, recipients r "
//            + "WHERE t.id = s.text_id AND t.id = r.text_id")
//    public LiveData<List<TextScheduleFull>> loadFullTextSchedules();
//
//    @Query("SELECT t.title AS textTitle, s.send_date AS sendDate "
//            + "FROM texts t, schedules s "
//            + "WHERE t.id = x.text_id")
//    public LiveData<List<TextScheduleShort>> loadTextAndSendDates();

    // If the @Insert method receives only 1 parameter, it can return a long,
    // which is the new rowId for the inserted item. If the parameter is an
    // array or a collection, it should return long[] or List<Long> instead.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long[] insertTexts(Text... texts);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long insertText(Text text);

    @Insert
    public long insert(Text text);

    // Although usually not necessary, you can have this method return an int
    // value instead, indicating the number of rows updated in the database.
    @Update
    public void updateTexts(Text... texts);

    @Delete
    void delete(Text text);


    // Although usually not necessary, you can have this method return an int
    // value instead, indicating the number of rows deleted in the database.
    @Delete
    void deleteAll(Text... texts);

    // You can also define this class in a separate file, as long as you add the
    // "public" access modifier.
    static class TextScheduleFull {
        public int tid;
        public String title;
        public String message;
        public String createdDate;
//        public Recipient recipient;
//        public String firstName;
//        public String lastName;
//        public String phone;
    }
    static class TextScheduleShort {
        public String textTitle;
        public String sendDate;
    }
}
