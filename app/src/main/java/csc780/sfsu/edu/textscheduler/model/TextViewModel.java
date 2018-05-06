package csc780.sfsu.edu.textscheduler.model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sp on 4/29/18.
 */

public class TextViewModel extends AndroidViewModel {

    private static final String TAG = "TextViewModel";

    private DataRepository mRepository;
    private LiveData<List<Text>> mAllTexts;

    public TextViewModel (Application application) {
        super(application);
        mRepository = new DataRepository(application);
        mAllTexts = mRepository.getAllTexts();
    }

    public LiveData<List<Text>> getAllTexts() { return mAllTexts; }
    public LiveData<List<Text>> getText(final int textId) { return mRepository.loadText(textId); }
    public LiveData<List<Schedule>> getSchedule(final int textId) { return mRepository.loadScheduleFromTextId(textId); }
    public LiveData<List<Recipient>> getRecipients(final int textId) { return mRepository.loadRecipients(textId); }

    public void insert(Text text) { mRepository.insert(text); }
    public void insert(Text text, Schedule schedule, Recipient recipient) {
        List<Object> list = new ArrayList<Object>();
        list.add(text);
        list.add(schedule);
        list.add(recipient);
        mRepository.insertAll(list);
    }
    public void insertSchedule(Schedule schedule) { mRepository.insertSchedule(schedule); }
    public void insertRecipient(Recipient recip) { mRepository.insertRecipient(recip); }
}
