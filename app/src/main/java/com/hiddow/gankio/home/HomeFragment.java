package com.hiddow.gankio.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.common.collect.Lists;
import com.hiddow.gankio.R;
import com.hiddow.gankio.base.BaseFragment;
import com.hiddow.gankio.model.object.Welfare;

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
    private List<Welfare> welfares = Lists.newArrayList();

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
        homeAdapter = new HomeAdapter(R.layout.activity_home, welfares);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.setAdapter(homeAdapter);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.fetchData();
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
}
