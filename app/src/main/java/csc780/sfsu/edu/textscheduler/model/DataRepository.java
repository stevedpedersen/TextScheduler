package csc780.sfsu.edu.textscheduler.model;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

/**
 * Created by sp on 4/29/18.
 */

public class DataRepository {

    private final AppDatabase mDb;
    private TextDao mTextDao;
    private ScheduleDao mScheduleDao;
    private RecipientDao mRecipientDao;
    private LiveData<List<Text>> mAllTexts;

    DataRepository(Application application) {
        mDb = AppDatabase.getDatabase(application);
        mTextDao = mDb.getTextDao();
        mScheduleDao = mDb.getScheduleDao();
        mRecipientDao = mDb.getRecipientDao();
//        mAllTexts = mTextDao.getAll();
    }

    /**
     * Get the list of texts from the database and get notified when the data changes.
     */
    public LiveData<List<Text>> getAllTexts() {
        return mTextDao.getAll();
    }
    public LiveData<List<Text>> loadText(final int textId) {
        return mTextDao.get(textId);
    }

    public LiveData<List<Schedule>> getAllSchedules() {
        return mScheduleDao.getAll();
    }
    public LiveData<List<Schedule>> loadScheduleFromTextId(final int textId) {
        return mScheduleDao.findByTextId(textId);
    }
    public LiveData<List<Schedule>> loadSchedule(final int schedId) {
        return mScheduleDao.get(schedId);
    }

    public LiveData<List<Recipient>> getAllRecipients() {
        return mRecipientDao.getAll();
    }
    public LiveData<List<Recipient>> loadRecipients(final int textId) {
        return mRecipientDao.findByTextId(textId);
    }

    /**
     * Insert async - Text, Schedule, Recipient
     */
    public void insert (Text text) {
        new insertTextAsyncTask(mTextDao).execute(text);
    }

    // Insert all 3 at the same time...
    public void insertAll (List<Object> list) {
        new insertListAsyncTask(mTextDao, mScheduleDao, mRecipientDao).execute(list);
    }

    public void insertSchedule (Schedule schedule) {
        new insertScheduleAsyncTask(mScheduleDao).execute(schedule);
    }
    public void insertRecipient (Recipient recipient) {
        new insertRecipientAsyncTask(mRecipientDao).execute(recipient);
    }

    /**
     * The Async insert tasks
     */
    private static class insertListAsyncTask extends AsyncTask<List<Object>, Void, Void> {

        private static final String TAG = "DataRepository";

        private TextDao mAsyncTaskTextDao;
        private ScheduleDao mAsyncTaskScheduleDao;
        private RecipientDao mAsyncTaskRecipientDao;

        insertListAsyncTask(TextDao tdao, ScheduleDao sdao, RecipientDao rdao) {
            mAsyncTaskTextDao = tdao;
            mAsyncTaskScheduleDao = sdao;
            mAsyncTaskRecipientDao = rdao;
        }

        @Override
        protected Void doInBackground( List<Object>... params) {

            Text text = (Text) params[0].get(0);
            long tid = mAsyncTaskTextDao.insert(text);

            Schedule schedule = (Schedule) params[0].get(1);
            schedule.setTextId((int) tid);
            mAsyncTaskScheduleDao.insert(schedule);

            Recipient recipient = (Recipient) params[0].get(2);
            recipient.setTextId((int) tid);
            mAsyncTaskRecipientDao.insert(recipient);

            return null;
        }
    }

    private static class insertTextAsyncTask extends AsyncTask<Text, Void, Void> {

        private TextDao mAsyncTaskTextDao;
        private ScheduleDao mAsyncTaskDao;

        insertTextAsyncTask(TextDao dao) {
            mAsyncTaskTextDao = dao;
        }

        @Override
        protected Void doInBackground(final Text... params) {
            mAsyncTaskTextDao.insert(params[0]);
            return null;
        }
    }

    private static class insertScheduleAsyncTask extends AsyncTask<Schedule, Void, Void> {

        private ScheduleDao mAsyncTaskScheduleDao;

        insertScheduleAsyncTask(ScheduleDao dao) {
            mAsyncTaskScheduleDao = dao;
        }

        @Override
        protected Void doInBackground(final Schedule... params) {
            mAsyncTaskScheduleDao.insert(params[0]);
            return null;
        }
    }

    private static class insertRecipientAsyncTask extends AsyncTask<Recipient, Void, Void> {

        private RecipientDao mAsyncTaskRecipientDao;

        insertRecipientAsyncTask(RecipientDao dao) {
            mAsyncTaskRecipientDao = dao;
        }

        @Override
        protected Void doInBackground(final Recipient... params) {
            mAsyncTaskRecipientDao.insert(params[0]);
            return null;
        }
    }
}
