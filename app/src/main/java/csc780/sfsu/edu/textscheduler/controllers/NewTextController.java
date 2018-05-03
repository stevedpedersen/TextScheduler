package csc780.sfsu.edu.textscheduler.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.OnClick;
import csc780.sfsu.edu.textscheduler.MainActivity;
import csc780.sfsu.edu.textscheduler.R;
import csc780.sfsu.edu.textscheduler.controllers.base.BaseController;
import csc780.sfsu.edu.textscheduler.util.BundleBuilder;

import butterknife.BindView;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class NewTextController extends BaseController {

    private static final String KEY_TEXT = "New Text Message.text";
    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

//    @BindView(R.id.tv_title) TextView tvTitle;
//    @BindView(R.id.recycler_view) RecyclerView recyclerView;
//    @BindView(R.id.edit_title) EditText tvTextTitle;
//    @BindView(R.id.edit_message) EditText tvMessage;
//    @BindView(R.id.edit_date) EditText tvDate;
//    @BindView(R.id.edit_first) EditText tvFirst;
//    @BindView(R.id.edit_last) EditText tvLast;
//    @BindView(R.id.edit_phone) EditText tvPhone;
//    @BindView(R.id.button_save) Button saveButton;

    private Context context;

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
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        NewTextView newTextView = new NewTextView(context);
        return newTextView;
    }

    @NonNull
    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_new_text, container, false);

//        return inflater.inflate(R.layout.recyclerview_item, container, false);
    }

    @Override
    public void onViewBound(@NonNull View view) {
        super.onViewBound(view);
//        tvTitle.setText(getArgs().getString(KEY_TEXT));
//        recyclerView.setHasFixedSize(true);
////        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
//        tvTextTitle.setText("");
//        tvTextTitle.setMovementMethod(LinkMovementMethod.getInstance());
//        tvMessage.setText("");
//        tvDate.setText("");
//        tvFirst.setText("");
//        tvLast.setText("");
//        tvPhone.setText("");
//        saveButton.setClickable(true);

        view.setBackgroundColor( getResources().getColor( R.color.grey_300));
    }



    @OnClick(R.id.button_save)
    void verifyFields() {
//        Intent replyIntent = new Intent();
//        if (TextUtils.isEmpty(mEditWordView.getText())) {
//            setResult(RESULT_CANCELED, replyIntent);
//        } else {
//            String word = mEditWordView.getText().toString();
//            replyIntent.putExtra(EXTRA_REPLY, word);
//            setResult(RESULT_OK, replyIntent);
//        }

//        saveButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                Intent replyIntent = new Intent();
//                if (TextUtils.isEmpty(mEditWordView.getText())) {
//                    setResult(RESULT_CANCELED, replyIntent);
//                } else {
//                    String word = mEditWordView.getText().toString();
//                    replyIntent.putExtra(EXTRA_REPLY, word);
//                    setResult(RESULT_OK, replyIntent);
//                }
//                finish();
//            }
//        });
    }
}
