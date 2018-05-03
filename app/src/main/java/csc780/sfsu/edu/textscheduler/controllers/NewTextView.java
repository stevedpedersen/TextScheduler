package csc780.sfsu.edu.textscheduler.controllers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import csc780.sfsu.edu.textscheduler.R;

/**
 * Created by sp on 5/2/18.
 */

public class NewTextView extends LinearLayout {
    private Context context;
    @BindView(R.id.tv_title) TextView tvTitle;
    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    @BindView(R.id.edit_title) EditText tvTextTitle;
    @BindView(R.id.edit_message) EditText tvMessage;
    @BindView(R.id.edit_date) EditText tvDate;
    @BindView(R.id.edit_first) EditText tvFirst;
    @BindView(R.id.edit_last) EditText tvLast;
    @BindView(R.id.edit_phone) EditText tvPhone;
    @BindView(R.id.button_save) Button saveButton;

    NewTextView(Context context) {
        super(context);
        this.context = context;
    }



}
