package csc780.sfsu.edu.textscheduler.controllers;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler;

import java.util.List;

import butterknife.ButterKnife;
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


    @BindView(R.id.edit_title) EditText editTitle;
    @BindView(R.id.edit_message) EditText editMessage;
    @BindView(R.id.edit_date) EditText editDate;
    @BindView(R.id.edit_first) EditText editFirst;
    @BindView(R.id.edit_last) EditText editLast;
    @BindView(R.id.edit_phone) EditText editPhone;

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

    @OnClick(R.id.button_save)
    public void processSubmission(View view) {
        Snackbar mySnackbar;
        Bundle newTextData = verifyFields();
        Intent newTextIntent = new Intent();

        if (saveText(newTextData)) {
            Log.d(TAG, "Text message saved successfully!");
            mySnackbar = Snackbar.make(editMessage,
                    R.string.success_saving, Snackbar.LENGTH_LONG);
            mySnackbar.show();
            getRouter().pushController(RouterTransaction.with(new HomeController((Activity) context))
                    .pushChangeHandler(new FadeChangeHandler())
                    .popChangeHandler(new FadeChangeHandler()));
        } else {
            mySnackbar = Snackbar.make(editMessage,
                    R.string.error_saving, Snackbar.LENGTH_SHORT);
            mySnackbar.show();
        }
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

        Log.d(TAG, bb.toString());

        return bb.build();
    }


}
