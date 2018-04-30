package csc780.sfsu.edu.textscheduler.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
//    @BindView(R.id.edit_title) TextView tvTextTitle;
//    @BindView(R.id.edit_message) TextView tvMessage;
//    @BindView(R.id.edit_date) TextView tvDate;
//    @BindView(R.id.edit_first) TextView tvFirst;
//    @BindView(R.id.edit_last) TextView tvLast;
//    @BindView(R.id.edit_phone) TextView tvPhone;
//    @BindView(R.id.button_save) Button saveButton;

    public NewTextController(String text) {
        this(new BundleBuilder(new Bundle())
                .putString(KEY_TEXT, text)
                .build()
        );
    }

    public NewTextController(Bundle args) {
        super(args);
    }

    @NonNull
    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_text, container, false);
    }

    @Override
    public void onViewBound(@NonNull View view) {
        super.onViewBound(view);
//        textView.setText(getArgs().getString(KEY_TEXT));
//        tvTitle.setText(getArgs().getString(KEY_TEXT));
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
