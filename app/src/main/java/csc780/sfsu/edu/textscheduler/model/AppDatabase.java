package csc780.sfsu.edu.textscheduler.model;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.Iterator;

/**
 * Created by sp on 4/16/18.
 */

@Database(entities = {Text.class, Schedule.class, Recipient.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    // Create database, populate with dummy data, build
//                    INSTANCE = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).addCallback(sRoomDatabaseCallback).build();
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "text_database").addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    public abstract TextDao getTextDao();
    public abstract ScheduleDao getScheduleDao();
    public abstract RecipientDao getRecipientDao();

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final TextDao mTextDao;
        private final ScheduleDao mScheduleDao;
        private final RecipientDao mRecipientDao;

        PopulateDbAsync(AppDatabase db) {
            mTextDao = db.getTextDao();
            mScheduleDao = db.getScheduleDao();
            mRecipientDao = db.getRecipientDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
//            deleteAll();
//            makeDummyData();
            return null;
        }

        private void deleteAll() {
            // deleteAll if you want to delete all our dummy data on start, then repopulate again
            Iterator<Recipient> recip = mRecipientDao.getAllSync().iterator();
            while (recip.hasNext()) {
                mRecipientDao.deleteAll(recip.next());
            }
            Iterator<Schedule> sched = mScheduleDao.getAllSync().iterator();
            while (sched.hasNext()) {
                mScheduleDao.deleteAll(sched.next());
            }
            Iterator<Text> text = mTextDao.getAllSync().iterator();
            while (text.hasNext()) {
                mTextDao.deleteAll(text.next());
            }
        }

        private void makeDummyData() {
            Text text = new Text();
            text.setTitle("Standard Happy Birthday Text");
            text.setMessage("Happy Birthday! You're so old now!");
            long textId = mTextDao.insertText(text);

            Recipient recipient = new Recipient((int) textId);
            recipient.setFirstName("Joe");
            recipient.setLastName("Shmoe");
            recipient.setPhone("415-555-5555");
            mRecipientDao.insert(recipient);

            Schedule schedule = new Schedule((int) textId);
            schedule.setSendDate("2018-04-20");
            mScheduleDao.insert(schedule);

            Text text2 = new Text();
            text2.setTitle("Happy Anniversary");
            text2.setMessage("Happy Anniversary! I love you more every year!\n\nxoxo");
            long textId2 = mTextDao.insertText(text2);

            Recipient recipient2 = new Recipient((int) textId2);
            recipient2.setFirstName("Joe");
            recipient2.setLastName("Shmoe");
            recipient2.setPhone("209-555-5555");
            mRecipientDao.insert(recipient2);

            Schedule schedule2 = new Schedule((int) textId2);
            schedule2.setSendDate("2019-03-20");
            mScheduleDao.insert(schedule2);

            Text text3 = new Text();
            text3.setTitle("Happy Father's Day");
            text3.setMessage("Happy fathers day, old man.\n\n-Steve");
            long textId3 = mTextDao.insertText(text3);

            Text text4 = new Text();
            text4.setTitle("Job Application Follow Up");
            text4.setMessage("Hello, just wanted to say thanks for the interview.\n\n-Steve Pedersen");
            long textId4 = mTextDao.insertText(text4);

        }
    }
}