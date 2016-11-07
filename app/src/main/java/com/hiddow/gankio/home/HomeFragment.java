package com.hiddow.gankio.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hiddow.gankio.R;
import com.hiddow.gankio.base.BaseFragment;
import com.hiddow.gankio.model.object.Welfare;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by yangxiaoguang on 2016/11/4.
 */

public class HomeFragment extends BaseFragment implements HomeContact.View {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private HomeContact.Presenter mPresenter;
    private HomeAdapter homeAdapter;
    private List<Welfare> welfares = new ArrayList<>();

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        homeAdapter = new HomeAdapter(R.layout.item_home, welfares);
        GridLayoutManager layoutManager = new GridLayoutManager(mActivity, 2);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(homeAdapter);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.fetchData();
            }
        });
        homeAdapter.openLoadMore(1, true);
        homeAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
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
    public void setPresenter(HomeContact.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showWelfare(List<Welfare> data) {
        welfares.clear();
        welfares.addAll(data);
        homeAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void addData(final List<Welfare> results) {
        homeAdapter.notifyDataChangedAfterLoadMore(results, true);
    }
}
