package csc780.sfsu.edu.textscheduler.controllers;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler;

import java.util.ArrayList;
import java.util.List;

import csc780.sfsu.edu.textscheduler.R;
import csc780.sfsu.edu.textscheduler.controllers.base.BaseController;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import csc780.sfsu.edu.textscheduler.model.Text;

public class DetailListPaneController extends BaseController {

    private static final String KEY_SELECTED_INDEX = "DetailListPaneController.selectedIndex";

    @BindView(R.id.tv_title) TextView tvTitle;
    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    @Nullable @BindView(R.id.detail_container) ViewGroup detailContainer;

    private int selectedIndex;
    private boolean twoPaneView;
    private List texts;
    private Text text;
    private int color;

    public DetailListPaneController() {}

    public DetailListPaneController(Text text, int color) {
        this.text = text;
        this.color = color;
        this.texts = new ArrayList<Text>();
        this.texts.add(text);
    }

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_detail_list_pane, container, false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);

        tvTitle.setText(text.getTitle());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new DetailItemAdapter(LayoutInflater.from(view.getContext()), texts));

        twoPaneView = (detailContainer != null);
        if (twoPaneView) {
            onRowSelected(selectedIndex);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(KEY_SELECTED_INDEX, selectedIndex);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        selectedIndex = savedInstanceState.getInt(KEY_SELECTED_INDEX);
    }

    @Override
    protected String getTitle() {
        return text.getTitle();
    }

    void onRowSelected(int index) {
        selectedIndex = index;

        ChildController controller = new ChildController(text, color, true);

        if (twoPaneView) {
            getChildRouter(detailContainer).setRoot(RouterTransaction.with(controller));
        } else {
            getRouter().pushController(RouterTransaction.with(controller)
                    .pushChangeHandler(new HorizontalChangeHandler())
                    .popChangeHandler(new HorizontalChangeHandler()));
        }
    }

    class DetailItemAdapter extends RecyclerView.Adapter<DetailItemAdapter.ViewHolder> {

        private final LayoutInflater inflater;
        private final List<Text> items;

        public DetailItemAdapter(LayoutInflater inflater, List<Text> items) {
            this.inflater = inflater;
            this.items = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(inflater.inflate(R.layout.row_detail_item, parent, false), items);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.bind(items.get(position), position);
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.row_root) View root;
            @BindView(R.id.tv_title) TextView tvTitle;
            private int position;
            private List<Text> texts;

            public ViewHolder(View itemView, List<Text> texts) {
                super(itemView);
                this.texts = texts;
                ButterKnife.bind(this, itemView);
            }

            void bind(Text item, int position) {
                tvTitle.setText(item.getTitle());
                this.position = position;

                if (twoPaneView && position == selectedIndex) {
                    root.setBackgroundColor(ContextCompat.getColor(root.getContext(), R.color.grey_400));
                } else {
                    root.setBackgroundColor(ContextCompat.getColor(root.getContext(), android.R.color.transparent));
                }
            }

            @OnClick(R.id.row_root)
            void onRowClick() {
                onRowSelected(position);
                notifyDataSetChanged();
            }

        }
    }

}
