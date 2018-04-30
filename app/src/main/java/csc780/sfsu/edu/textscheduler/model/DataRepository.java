package csc780.sfsu.edu.textscheduler.model;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

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


    /**
     * Insert async
     */
    public void insert (Text text) {
        new insertAsyncTask(mTextDao).execute(text);
    }

    private static class insertAsyncTask extends AsyncTask<Text, Void, Void> {

        private TextDao mAsyncTaskDao;

        insertAsyncTask(TextDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Text... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

//    public static DataRepository getInstance(final AppDatabase database) {
//        if (sInstance == null) {
//            synchronized (DataRepository.class) {
//                if (sInstance == null) {
//                    sInstance = new DataRepository(database);
//                }
//            }
//        }
//        return sInstance;
//    }

}
