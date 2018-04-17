package csc780.sfsu.edu.textscheduler;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by sp on 4/16/18.
 */

@Database(entities = {Text.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TextDao textDao();
}