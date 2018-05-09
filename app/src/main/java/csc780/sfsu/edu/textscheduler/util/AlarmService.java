package csc780.sfsu.edu.textscheduler.util;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.widget.Toast;



/**
 * Created by sp on 5/6/18.
 */

public class AlarmService extends Service {


    String smsNumberToSend, smsTextToSend;

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub

        Toast.makeText(this, "AlarmService.onCreate()", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        Toast.makeText(this, "AlarmService.onDestroy()", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStart(Intent intent, int startId) {

        Toast.makeText(this, "AlarmService.onStart()", Toast.LENGTH_LONG).show();
        // TODO Auto-generated method stub
        //super.onStart(intent, startId);

        Bundle bundle = intent.getExtras();
        smsNumberToSend = (String) bundle.getCharSequence("Number");
        smsTextToSend = (String) bundle.getCharSequence("Message");

        Toast.makeText(this, "MyAlarmService.onStart()", Toast.LENGTH_LONG).show();
        Toast.makeText(this,
                "MyAlarmService.onStart() with \n" +
                        "smsNumberToSend = " + smsNumberToSend + "\n" +
                        "smsTextToSend = " + smsTextToSend,
                Toast.LENGTH_LONG).show();

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(smsNumberToSend, null, smsTextToSend, null, null);

    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }
}
