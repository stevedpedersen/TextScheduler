package csc780.sfsu.edu.textscheduler.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
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

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import csc780.sfsu.edu.textscheduler.MainActivity;
import csc780.sfsu.edu.textscheduler.R;
import csc780.sfsu.edu.textscheduler.controllers.base.BaseController;
import csc780.sfsu.edu.textscheduler.util.BundleBuilder;

import butterknife.BindView;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;

public class NewTextController extends BaseController {

    private static final String KEY_TEXT = "New Text Message.text";
    public static final String EXTRA_NEW = "textschedulesql.NEW";

//    @BindView(R.id.tv_title) TextView tvTitle;
//    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    @BindView(R.id.edit_title) EditText editTitle;
    @BindView(R.id.edit_message) EditText editMessage;
    @BindView(R.id.edit_date) EditText editDate;
    @BindView(R.id.edit_first) EditText editFirst;
    @BindView(R.id.edit_last) EditText editLast;
    @BindView(R.id.edit_phone) EditText editPhone;

    private Context context;
    private Unbinder unbinder;


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
    }


    @OnClick(R.id.button_save)
    public void verifyFields(View view) {
        System.out.println("CHEESEEEEEEEEE");
        Intent newTextIntent = new Intent();
        String title = "";
        String message = "";
        String first = "";
        String last = "";
        String phone = "";
        String[] values = {};
        BundleBuilder bb = new BundleBuilder(new Bundle());
        Snackbar mySnackbar = null;

        if (TextUtils.isEmpty(editTitle.getText())) {
            mySnackbar = Snackbar.make(editTitle,
                    R.string.empty_title_not_saved, Snackbar.LENGTH_SHORT);
            mySnackbar.show();
        } else {
            title = editTitle.getText().toString();
            bb.putString("title", title);
//            replyIntent.putExtra(EXTRA_NEW, title);
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


        newTextIntent.putExtras(bb.build());

        if (newTextIntent.getExtras() != null) {
            Log.d(TAG, newTextIntent.getExtras().toString());
        }


    }

}
