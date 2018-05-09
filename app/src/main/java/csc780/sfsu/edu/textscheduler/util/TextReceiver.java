package csc780.sfsu.edu.textscheduler.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;

import java.util.ArrayList;

/**
 * Created by sp on 5/6/18.
 */

public class TextReceiver extends BroadcastReceiver {

    private String message;
    private String phone;
    private long textId;
    private long recipientId;

    @Override
    public void onReceive(Context context, Intent intent) {

        SmsManager smsManager = SmsManager.getDefault();
        ArrayList<String> parts;
        int msgSize;

        message = intent.getStringExtra("MESSAGE");
        phone = intent.getStringExtra("NUMBER");
        textId = intent.getLongExtra("TEXT_ID", 0);
        recipientId = intent.getLongExtra("RECIPIENT_ID", 0);
    }

}
