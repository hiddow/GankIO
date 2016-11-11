package com.hiddow.gankio.home.bottomView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.common.collect.Lists;
import com.hiddow.gankio.ApplicationModule;
import com.hiddow.gankio.GankIoApplication;
import com.hiddow.gankio.R;
import com.hiddow.gankio.model.object.SearchResult;
import com.hiddow.gankio.network.ApiBaseComponent;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by yangxiaoguang on 2016/11/11.
 */

public class BottomSheetView implements SearchViewContact.View {

    View view;
    RecyclerView mRecyclerView;
    @Inject
    SearchViewPresenter mPresenter;
    private List<SearchResult> mData = Lists.newArrayList();
    private SearViewAdapter mAdapter;
    private BottomSheetBehavior behavior;
    public BottomSheetView(Context context, View view, String keyWord, String category) {
        this.view = view;
        ApiBaseComponent component = ((GankIoApplication) context.getApplicationContext()).getComponent();
        DaggerSearchViewPresenterComponent.builder()
                .applicationModule(new ApplicationModule(context))
                .searchViewPresenterModule(new SearchViewPresenterModule(this))
                .apiBaseComponent(component)
                .build()
                .inject(this);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mAdapter = new SearViewAdapter(R.layout.item_info, mData);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(context, layoutManager.getOrientation()));
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPresenter.loadMore();
            }
        });
        mAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                SearchResult info = mData.get(i);
                mPresenter.porformItemClick(info);
            }
        });
        behavior = BottomSheetBehavior.from(this.view);
        mPresenter.fetchData(keyWord,category);
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    behavior.setPeekHeight(0);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

    }

    @Override
    public void showData(List<SearchResult> data) {
        mData.clear();
        mData.addAll(data);
        mAdapter.notifyDataSetChanged();
        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    @Override
    public void addData(List<SearchResult> results) {

    }

    @Override
    public void setPresenter(SearchViewContact.Presenter presenter) {

    }
}
