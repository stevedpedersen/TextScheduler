package csc780.sfsu.edu.textscheduler.controllers;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler;

import java.util.Calendar;

import butterknife.OnClick;
import butterknife.Unbinder;
import csc780.sfsu.edu.textscheduler.R;
import csc780.sfsu.edu.textscheduler.controllers.base.BaseController;
import csc780.sfsu.edu.textscheduler.model.Recipient;
import csc780.sfsu.edu.textscheduler.model.Schedule;
import csc780.sfsu.edu.textscheduler.model.Text;
import csc780.sfsu.edu.textscheduler.model.TextViewModel;
import csc780.sfsu.edu.textscheduler.util.BundleBuilder;

import butterknife.BindView;

public class NewTextController extends BaseController {

    private static final String TAG = "NewTextController";
    private static final String KEY_TEXT = "New Text Message.text";
    public static final String EXTRA_NEW = "textschedulesql.NEW";

    @BindView(R.id.edit_phone) EditText editPhone;
    @BindView(R.id.edit_title) EditText editTitle;
    @BindView(R.id.edit_message) EditText editMessage;
    @BindView(R.id.edit_date) EditText editDate;
    @BindView(R.id.edit_first) EditText editFirst;
    @BindView(R.id.edit_last) EditText editLast;
    private Context context;
    private Unbinder unbinder;
    private TextViewModel mTextViewModel;


    public NewTextController(Context context) {
        this(new BundleBuilder(new Bundle())
                .putString(KEY_TEXT, "")
                .build()
        );
        this.context = context;
    }

    public NewTextController(Bundle args) {
        super(args);
    }

    @NonNull
    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
//        return inflater.inflate(R.layout.controller_new_text, container, false);
        return inflater.inflate(R.layout.view_new_text, container, false);
    }

    @Override
    public void onViewBound(@NonNull View view) {
        super.onViewBound(view);
        view.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.grey_300));
        getActionBar().setTitle(R.string.new_text_title);
    }

//    @OnClick(R.id.pick_date)
//    public void showDatePickerDialog(View view) {
//        DialogFragment newFragment = new DatePickerFragment();
//        Bundle bundle = new BundleBuilder(new Bundle()).putInt("id", R.id.edit_date).build();
//        newFragment.setArguments(bundle);
//        newFragment.show(getActivity().getFragmentManager(), "datePicker");
//    }
//
//    @OnClick(R.id.pick_time)
//    public void showTimePickerDialog(View view) {
//        DialogFragment newFragment = new TimePickerFragment();
//        Bundle bundle = new BundleBuilder(new Bundle()).putInt("id", R.id.edit_date).build();
//        newFragment.setArguments(bundle);
//        newFragment.show(getActivity().getFragmentManager(), "timePicker");
//    }

    class MyOnDateChangedListener implements DatePicker.OnDateChangedListener {
        @Override
        public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Log.d(TAG, "somehow got here lol");
            editDate.setText( "" + dayOfMonth + "-" + (monthOfYear + 1) + "-" + year );
        }
    }


    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        private int hour;
        private int minute;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Log.d(TAG, "onCreateDialog in TPF");
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            hour = c.get(Calendar.HOUR_OF_DAY);
            minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            int id = getArguments().getInt("id");
            hour = hourOfDay;
            this.minute = minute;
//            String display = (new StringBuilder()
//                    .append(editDate.getText())
//                    .append(" ").append(hourOfDay)
//                    .append(":").append(minute)).toString();
//            editDate.setText(display);
        }
        public String getTime() {
            return hour + ":" + minute;
        }
    }
    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        private int year;
        private int month;
        private int day;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH);
            day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            this.year = year;
            this.month = month;
            this.day = day;
//            String display = (new StringBuilder()
//                    .append(year).append("-")
//                    .append(month).append("-")
//                    .append(day).append(editDate.getText())).toString();
//            editDate.setText(display);
        }
        public String getDate() {
            return year + "-" + month + "-" + day;
        }
    }

    @OnClick(R.id.button_save)
    public void processSubmission(View view) {
        Snackbar mySnackbar;
        Bundle newTextData = verifyFields();
        Boolean success = saveText(newTextData);
        Log.d(TAG, "Save text success status: " + success);

        if (success) {
            Log.d(TAG, "Text message saved successfully!");
            mySnackbar = Snackbar.make(editMessage,
                    R.string.success_saving, Snackbar.LENGTH_LONG);
            mySnackbar.show();

//            sendText(textId);
//            sendSMS("18383000684", "Cheese is very good.");

            Log.d(TAG, "after sendText()");
            getRouter().pushController(RouterTransaction.with(new HomeController((Activity) context))
                    .pushChangeHandler(new FadeChangeHandler())
                    .popChangeHandler(new FadeChangeHandler()));
        } else {
            mySnackbar = Snackbar.make(editMessage,
                    R.string.error_saving, Snackbar.LENGTH_SHORT);
            mySnackbar.show();
        }
    }

    private void sendText(int textId) {
//        LiveData<List<Text>> text = mTextViewModel.getText(textId);
//        LiveData<List<Recipient>> recip = mTextViewModel.getRecipients(textId);
//        Text text = (Text) mTextViewModel.getText(textId).getValue();
//        Recipient recip = (Recipient) mTextViewModel.getRecipients(textId).getValue();
        String testPhone = "18383000684";

        Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( "sms:" + testPhone ) );
        intent.putExtra( "sms_body", "cheese is good!" );
        startActivity( intent );
    }

    //Sends an SMS message to another device-
    private void sendSMS(String phoneNumber, String message) {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
    }

    private Boolean saveText(Bundle data) {
        Boolean success = false;
        mTextViewModel = ViewModelProviders.of((FragmentActivity) context).get(TextViewModel.class);

        try {
            Text text = new Text();
            text.setTitle(data.get("title").toString());
            text.setMessage(data.get("message").toString());

            Schedule schedule = new Schedule();
            schedule.setSendDate(data.get("date").toString());

            Recipient recipient = new Recipient();
            recipient.setFirstName(data.get("first").toString());
            recipient.setLastName(data.get("last").toString());
            recipient.setPhone(data.get("phone").toString());

            // insert all at once so text id can be used as a foreign key
            mTextViewModel.insert(text, schedule, recipient);
            success = true;

        } catch (Exception e) {
            Log.d(TAG, "Error saving to database");
        }

        return success;
    }

//    private void scheduleText(Text text, Schedule schedule, Recipient recipient) {
//        int Hour = Time_Picker.getCurrentHour();
//        int Minute = Time_Picker.getCurrentMinute();
//
//        DatePicker Date_Picker = (DatePicker)findViewById(R.id.datePicker1);
//        int day = Date_Picker.getDayOfMonth();
//        int month = Date_Picker.getMonth() + 1;
//        int year = Date_Picker.getYear();
//
////        Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( "sms:" + testPhone ) );
////        intent.putExtra( "sms_body", "cheese is good!" );
////        startActivity( intent );
//
////        intent = new Intent(context, SMSHandleReceiver.class);
////        intent.setAction(Constants.PRIVATE_SMS_ACTION); // com.smsschedulerexpl.android.private_sms_action
////        intent.putExtra("SMS_ID", cur.getLong(cur.getColumnIndex(DBAdapter.KEY_ID)));
////        intent.putExtra("RECIPIENT_ID", cur.getLong(cur.getColumnIndex(DBAdapter.KEY_RECIPIENT_ID)));
////        intent.putExtra("NUMBER", cur.getString(cur.getColumnIndex(DBAdapter.KEY_NUMBER)));
////        intent.putExtra("MESSAGE", cur.getString(cur.getColumnIndex(DBAdapter.KEY_MESSAGE)));
//
//
//
//
//        Intent myIntent = new Intent(ScheduleMessage.this, recieve.class);
//
//        Bundle bundle = new Bundle();
//        bundle.putCharSequence("Number", Number.getText().toString());
//        bundle.putCharSequence("Message", Message.getText().toString());
//        myIntent.putExtras(bundle);
//
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(ScheduleMessage.this, 0, myIntent, 0);
//
//        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(System.currentTimeMillis());
//        //calendar.add(Calendar.SECOND, 10);
//        calendar.set(year, month, day, Hour, Minute);
//
//        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
//
//        Toast.makeText(ScheduleMessage.this,"Start Alarm with \n" +
//                        "smsNumber = " + Number.getText().toString() +
//                        "\n" + "smsText = " + Message.getText().toString() + "\nScheduled for :"
//                        + Hour +" "+Minute,
//                Toast.LENGTH_LONG).show();
//    }

    public Bundle verifyFields() {

        String title = "";
        String message = "";
        String first = "";
        String last = "";
        String phone = "";
        BundleBuilder bb = new BundleBuilder(new Bundle());
        Snackbar mySnackbar = null;

        if (TextUtils.isEmpty(editTitle.getText())) {
            mySnackbar = Snackbar.make(editTitle,
                    R.string.empty_title_not_saved, Snackbar.LENGTH_SHORT);
            mySnackbar.show();
        } else {
            title = editTitle.getText().toString();
            bb.putString("title", title);
        }
        if (!title.equals("") && TextUtils.isEmpty(editMessage.getText())) {
            mySnackbar = Snackbar.make(editMessage,
                    R.string.empty_message_not_saved, Snackbar.LENGTH_SHORT);
            mySnackbar.show();
        } else {
            message = editMessage.getText().toString();
            bb.putString("message", message);
        }
        if (TextUtils.isEmpty(editDate.getText())) {
            // no date
        } else {
            String date = editDate.getText().toString();
            bb.putString("date", date);
        }
        if (!message.equals("") && TextUtils.isEmpty(editFirst.getText())) {
            mySnackbar = Snackbar.make(editFirst,
                    R.string.empty_firstname_not_saved, Snackbar.LENGTH_SHORT);
            mySnackbar.show();
        } else {
            first = editFirst.getText().toString();
            bb.putString("first", first);
        }
        if (TextUtils.isEmpty(editLast.getText())) {
            // no lastname
        } else {
            last = editLast.getText().toString();
            bb.putString("last", last);
        }
        if (!first.equals("") && TextUtils.isEmpty(editPhone.getText())) {
            mySnackbar = Snackbar.make(editPhone,
                    R.string.empty_phone_not_saved, Snackbar.LENGTH_SHORT);
            mySnackbar.show();
        } else {
            phone = editPhone.getText().toString();
            bb.putString("phone", phone);
        }

//        Log.d(TAG, bb.toString());

        return bb.build();
    }


}
