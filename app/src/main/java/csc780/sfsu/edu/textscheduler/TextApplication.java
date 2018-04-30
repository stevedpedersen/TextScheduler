package csc780.sfsu.edu.textscheduler;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

public class TextApplication extends Application {

    public static RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        refWatcher = LeakCanary.install(this);
    }

}
