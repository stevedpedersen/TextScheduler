package csc780.sfsu.edu.textscheduler.controllers;

import android.annotation.TargetApi;
import android.os.Build.VERSION_CODES;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import csc780.sfsu.edu.textscheduler.R;
import csc780.sfsu.edu.textscheduler.changehandler.ScaleFadeChangeHandler;
import csc780.sfsu.edu.textscheduler.controllers.base.BaseController;
import csc780.sfsu.edu.textscheduler.widget.ElasticDragDismissFrameLayout;
import csc780.sfsu.edu.textscheduler.widget.ElasticDragDismissFrameLayout.ElasticDragDismissCallback;

@TargetApi(VERSION_CODES.LOLLIPOP)
public class DragDismissController extends BaseController {

    private final ElasticDragDismissCallback dragDismissListener = new ElasticDragDismissCallback() {
        @Override
        public void onDragDismissed() {
            overridePopHandler(new ScaleFadeChangeHandler());
            getRouter().popController(DragDismissController.this);
        }
    };

    @NonNull
    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_drag_dismiss, container, false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        ((ElasticDragDismissFrameLayout)view).addListener(dragDismissListener);
    }

    @Override
    protected void onDestroyView(@NonNull View view) {
        super.onDestroyView(view);

        ((ElasticDragDismissFrameLayout)view).removeListener(dragDismissListener);
    }

    @Override
    protected String getTitle() {
        return "Drag to Dismiss";
    }
}
