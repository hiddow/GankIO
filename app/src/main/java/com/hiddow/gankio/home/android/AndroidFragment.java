package com.hiddow.gankio.home.android;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.common.collect.Lists;
import com.hiddow.gankio.GankIoApplication;
import com.hiddow.gankio.R;
import com.hiddow.gankio.base.BaseFragment;
import com.hiddow.gankio.model.object.AndroidInfo;
import com.hiddow.gankio.network.ApiBaseComponent;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by yangxiaoguang on 2016/11/10.
 */

public class AndroidFragment extends BaseFragment implements AndroidContact.View {
    @Inject
    AndroidPresenter mPresenter;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private AndroidInfoAdapter mAdapter;
    private List<AndroidInfo> mData = Lists.newArrayList();

    public static AndroidFragment newInstance() {

        Bundle args = new Bundle();
        AndroidFragment fragment = new AndroidFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ApiBaseComponent component = ((GankIoApplication) getActivity().getApplication()).getComponent();
        DaggerAndroidPresenterComponent.builder()
                .apiBaseComponent(component)
                .androidPresenterModule(new AndroidPresenterModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        mAdapter = new AndroidInfoAdapter(R.layout.item_info, mData);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mActivity,layoutManager.getOrientation()));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.fetchData();
            }
        });
        mAdapter.openLoadMore(1, true);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPresenter.loadMore();
            }
        });

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mPresenter.fetchData();
    }

    @Override
    public void showData(List data) {
        mData.clear();
        mData.addAll(data);
        mAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void addData(List results) {
        mAdapter.notifyDataChangedAfterLoadMore(results, true);
    }

    @Override
    public void setPresenter(AndroidContact.Presenter presenter) {

    }
}
