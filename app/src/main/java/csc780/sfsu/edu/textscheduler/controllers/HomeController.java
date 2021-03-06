package csc780.sfsu.edu.textscheduler.controllers;

import android.app.Activity;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bluelinelabs.conductor.ControllerChangeHandler;
import com.bluelinelabs.conductor.ControllerChangeType;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler;

import java.util.List;
import csc780.sfsu.edu.textscheduler.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import csc780.sfsu.edu.textscheduler.controllers.base.BaseController;
import csc780.sfsu.edu.textscheduler.model.Text;
import csc780.sfsu.edu.textscheduler.model.TextViewModel;

public class HomeController extends BaseController {

    private Activity activity;
    private LifecycleOwner lifecycleOwner;
    private TextViewModel mTextViewModel;
    private int[] colors;

    private static final String TAG = "HomeController";
    private static final String KEY_FAB_VISIBILITY = "HomeController.fabVisibility";

    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    @BindView(R.id.fab) View fab;

    public HomeController() {}
    public HomeController(Activity activity) {
        this.lifecycleOwner = (LifecycleOwner) activity;
        this.activity = activity;
        setHasOptionsMenu(true);
        setColors();
    }

    @NonNull
    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        Log.d(TAG, "------ in inflateView()");
        return inflater.inflate(R.layout.controller_home, container, false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);

        final TextListAdapter adapter = new TextListAdapter(view.getContext(), colors);
        mTextViewModel = ViewModelProviders.of((FragmentActivity) getActivity()).get(TextViewModel.class);
        mTextViewModel.getAllTexts().observe(lifecycleOwner, new Observer<List<Text>>() {
            @Override
            public void onChanged(@Nullable final List<Text> texts) {
                // Update the cached copy of the texts in the adapter.
                adapter.setTexts(texts);
            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        Log.d(TAG, "------ in onViewBound()");
    }

    @Override
    protected void onSaveViewState(@NonNull View view, @NonNull Bundle outState) {
        super.onSaveViewState(view, outState);
        outState.putInt(KEY_FAB_VISIBILITY, fab.getVisibility());
//        outState.putInt("recyclerview", recyclerView.getVisibility());
//        mTextViewModel.getAllTexts().removeObservers(lifecycleOwner);
        Log.d(TAG, "------ in onSaveViewState()");
    }

    @Override
    protected void onRestoreViewState(@NonNull View view, @NonNull Bundle savedViewState) {
        super.onRestoreViewState(view, savedViewState);
        Log.d(TAG, "------ in onRestoreViewState()");

        //noinspection WrongConstant
        fab.setVisibility(savedViewState.getInt(KEY_FAB_VISIBILITY));
//        recyclerView.setVisibility(savedViewState.getInt("recyclerview"));
    }

//    @Override
//    protected void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//        Log.d(TAG, "------ in onSaveInstanceState()");
//
//        outState.putInt(KEY_FAB_VISIBILITY, fab.getVisibility());
//        outState.putInt("recyclerview", recyclerView.getVisibility());
//    }
//
//    @Override
//    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        Log.d(TAG, "------ in onRestoreInstanceState()");
//
//        fab.setVisibility(savedInstanceState.getInt(KEY_FAB_VISIBILITY));
//        recyclerView.setVisibility(savedInstanceState.getInt("recyclerview"));
//    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.home, menu);
        Log.d(TAG, "------ in onCreateOptionsMenu()");
    }

    @Override
    protected void onChangeStarted(@NonNull ControllerChangeHandler changeHandler, @NonNull ControllerChangeType changeType) {
        setOptionsMenuHidden(!changeType.isEnter);
        Log.d(TAG, "------ in onChangeStarted()");
        if (changeType.isEnter) {
            setTitle();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.about) {
            onFabClicked(false);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected String getTitle() {
        return "Scheduled Text Messages";
    }

    @OnClick(R.id.fab)
    protected void onFabClicked() {
        onFabClicked(true);
    }

    private void onFabClicked(boolean fromFab) {
        getRouter().pushController(RouterTransaction.with(new NewTextController(activity))
                .pushChangeHandler(new FadeChangeHandler())
                .popChangeHandler(new FadeChangeHandler()));

    }

    private void onModelRowClick(Text text, int position, int color) {
        getRouter().pushController(RouterTransaction.with(new DetailListPaneController(text, color))
                .pushChangeHandler(new FadeChangeHandler())
                .popChangeHandler(new FadeChangeHandler()));
    }

    private void setColors() {
        int[] colors = {R.color.red_300, R.color.blue_grey_300, R.color.purple_300,
                R.color.orange_300, R.color.green_300, R.color.pink_300, R.color.deep_orange_300,
                R.color.grey_300, R.color.lime_300, R.color.teal_300};
        this.colors = colors;
    }

    // ********************************************************************************************
    public class TextListAdapter extends RecyclerView.Adapter<TextListAdapter.TextViewHolder> {

        private final LayoutInflater mInflater;
        private List<Text> mTexts; // Cached copy of texts
        private final int[] colors;

        TextListAdapter(Context context, int[] colors) {
            mInflater = LayoutInflater.from(context);
            this.colors = colors;
        }

        //    ********************************************************************
        class TextViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.tv_title) TextView tvTitle;
            @BindView(R.id.img_dot) ImageView imgDot;
            private Text text;
            private int position;
            private int[] colors;
            private int color;

            private TextViewHolder(View itemView, int[] colors) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                this.colors = colors;
            }

            void bind(int position, Text item) {
                text = item;
                this.color = colors[position % colors.length];
                tvTitle.setText(text.getTextShortSummary());
                imgDot.getDrawable().setColorFilter(ContextCompat.getColor(getActivity(), this.color), Mode.SRC_ATOP);
                this.position = position;

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    tvTitle.setTransitionName(getResources().getString(R.string.transition_tag_title_indexed, position));
                    imgDot.setTransitionName(getResources().getString(R.string.transition_tag_dot_indexed, position));
                }
            }

            @OnClick(R.id.row_root)
            void onRowClick() {
                onModelRowClick(text, position, color);
            }
        }

        @Override
        public TextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
            return new TextListAdapter.TextViewHolder(itemView, this.colors);
        }

        @Override
        public void onBindViewHolder(TextViewHolder holder, int position) {
//            holder.bind(position, mTexts.get(position));
            if (mTexts != null) {
                Text current = mTexts.get(position);
                if (holder.tvTitle != null) {
                    holder.tvTitle.setText(current.getTextShortSummary());
                }

                holder.bind(position, current);
            } else {
                // Covers the case of data not being ready yet.
                holder.tvTitle.setText("No Text");
            }
        }

        void setTexts(List<Text> texts){
            mTexts = texts;
            notifyDataSetChanged();
        }

        // getItemCount() is called many times, and when it is first called,
        // mTexts has not been updated (means initially, it's null, and we can't return null).
        @Override
        public int getItemCount() {
            if (mTexts != null)
                return mTexts.size();
            else return 0;
        }
    }


}
