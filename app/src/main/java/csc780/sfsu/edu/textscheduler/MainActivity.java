package csc780.sfsu.edu.textscheduler;;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;

import java.util.List;

import csc780.sfsu.edu.textscheduler.controllers.base.*;
import csc780.sfsu.edu.textscheduler.controllers.*;

import butterknife.BindView;
import butterknife.ButterKnife;
import csc780.sfsu.edu.textscheduler.ActionBarProvider;
import csc780.sfsu.edu.textscheduler.R;
import csc780.sfsu.edu.textscheduler.model.AppDatabase;
import csc780.sfsu.edu.textscheduler.model.Text;
import csc780.sfsu.edu.textscheduler.model.TextViewModel;

public final class MainActivity extends AppCompatActivity implements ActionBarProvider {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.controller_container) ViewGroup container;
    private TextViewModel mTextViewModel;

    private Router router;
    private AppDatabase mDb;
    private static final int NEW_TEXT_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
//        Intent intent = new Intent(MainActivity.this, NewTextController.class);
//        startActivityForResult(intent, NEW_TEXT_ACTIVITY_REQUEST_CODE);

        router = Conductor.attachRouter(this, container, savedInstanceState);
        if (!router.hasRootController()) {
            HomeController homeController = new HomeController(this);
            router.setRoot(RouterTransaction.with(homeController));
        }
    }

    @Override
    public void onBackPressed() {
        if (!router.handleBack()) {
            super.onBackPressed();
        }
    }

}
