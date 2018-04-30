package csc780.sfsu.edu.textscheduler.model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

/**
 * Created by sp on 4/29/18.
 */

public class TextViewModel extends AndroidViewModel {
    private DataRepository mRepository;

    private LiveData<List<Text>> mAllTexts;

    public TextViewModel (Application application) {
        super(application);
        mRepository = new DataRepository(application);
        mAllTexts = mRepository.getAllTexts();
    }

    public LiveData<List<Text>> getAllTexts() { return mAllTexts; }

    public void insert(Text text) { mRepository.insert(text); }
}
