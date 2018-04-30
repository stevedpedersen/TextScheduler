package csc780.sfsu.edu.textscheduler;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import csc780.sfsu.edu.textscheduler.model.AppDatabase;
import csc780.sfsu.edu.textscheduler.model.Schedule;
import csc780.sfsu.edu.textscheduler.model.ScheduleDao;
import csc780.sfsu.edu.textscheduler.model.Text;
import csc780.sfsu.edu.textscheduler.model.TextDao;
import csc780.sfsu.edu.textscheduler.model.Recipient;
import csc780.sfsu.edu.textscheduler.model.RecipientDao;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by sp on 4/17/18.
 */

@RunWith(AndroidJUnit4.class)
public class SimpleEntityReadWriteTest {
    private TextDao mTextDao;
    private ScheduleDao mScheduleDao;
    private RecipientDao mRecipientDao;
    private AppDatabase mDb;

    @Before
    public void createDb() {
//        Context context = InstrumentationRegistry.getTargetContext();
//        mDb = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
//        mTextDao = mDb.getTextDao();
//        mScheduleDao = mDb.getScheduleDao();
//        mRecipientDao = mDb.getRecipientDao();
    }

    @After
    public void closeDb() throws IOException {
//        mDb.close();
    }

    @Test
    public void writeUserAndReadInList() throws Exception {
//        Text text = new Text(); //TestUtil.createText();
//        text.setTitle("Standard Happy Birthday Text");
//        text.setMessage("Happy Birthday! You're so old now!\n\n-Steve\nxoxo");
//        long textId = mTextDao.insertText(text);
//
//        Recipient recipient = new Recipient((int) textId);
//        recipient.setFirstName("Joe");
//        recipient.setLastName("Shmoe");
//        recipient.setPhone("415-555-5555");
//        mRecipientDao.insert(recipient);
//
//        Schedule schedule = new Schedule((int) textId);
//        schedule.setSendDate("2018-04-20");
//        mScheduleDao.insert(schedule);
//
//        List<Text> byTitle = mTextDao.findTextsByTitle("Standard Happy Birthday Text");
//        assertThat(byTitle.get(0).getMessage(), equalTo(text.getMessage()));
    }
}
