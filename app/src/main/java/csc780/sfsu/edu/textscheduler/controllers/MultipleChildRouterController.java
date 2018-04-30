package csc780.sfsu.edu.textscheduler.controllers;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import csc780.sfsu.edu.textscheduler.R;
import csc780.sfsu.edu.textscheduler.controllers.base.BaseController;

import butterknife.BindViews;

public class MultipleChildRouterController extends BaseController {

    @BindViews({R.id.container_0, R.id.container_1, R.id.container_2}) ViewGroup[] childContainers;

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_multiple_child_routers, container, false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);
    }

    @Override
    protected String getTitle() {
        return "Child Router Demo";
    }

}
