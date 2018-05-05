package csc780.sfsu.edu.textscheduler.controllers;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import csc780.sfsu.edu.textscheduler.R;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

/**
 * Created by sp on 5/2/18.
 */

public class NewTextView extends LinearLayout {

    private final static String TAG = "NewTextView";
    private Context context;
    private RecyclerView.LayoutManager mLayoutManager;


//    @BindView(R.id.tv_title) TextView tvTitle;
//    @BindView(R.id.recycler_view) RecyclerView recyclerView;
//    @BindView(R.id.edit_title) EditText editTitle;
//    @BindView(R.id.edit_message) EditText editMessage;
//    @BindView(R.id.edit_date) EditText editDate;
//    @BindView(R.id.edit_first) EditText editFirst;
//    @BindView(R.id.edit_last) EditText editLast;
//    @BindView(R.id.edit_phone) EditText editPhone;
//    @BindView(R.id.button_save) Button saveButton;

    NewTextView(Context context, @Nullable AttributeSet attributes) {
        super(context);
        this.context = context;
//        editTitle.addTextChangedListener(this);
//        editMessage.addTextChangedListener(this);
//        editDate.addTextChangedListener(this);
//        editFirst.addTextChangedListener(this);
//        editLast.addTextChangedListener(this);
//        editPhone.addTextChangedListener(this);
//        editTitle.addTextChangedListener(this);

        mLayoutManager = new LinearLayoutManager(context);
    }

//    @Override
//    protected void onFinishInflate() {
//        super.onFinishInflate();
//        ButterKnife.bind(this);
//    }
//
//    @Override
//    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//    }
//
//    @Override
//    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//        System.out.println("ya baby here");
//        // If nothing is entered, display all friends.
//        if (charSequence.length() == 0) {
//            Log.d(TAG, "Text changed: sequence length = 0");
//
//            // If something is entered into the search box, display the friends or search for a friend in the database
//            // if the user isn't currently friends with them.
//        } else {
//            Log.d(TAG, "Text changed: charSeq = " + charSequence);
//            System.out.println("ya baby "+ charSequence);
//        }
//    }
//
//    @Override
//    public void afterTextChanged(Editable editable) {
//
//    }


}
