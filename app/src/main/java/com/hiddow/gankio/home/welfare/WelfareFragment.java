package com.hiddow.gankio.home.welfare;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hiddow.gankio.GankIoApplication;
import com.hiddow.gankio.R;
import com.hiddow.gankio.base.BaseFragment;
import com.hiddow.gankio.model.object.Welfare;
import com.hiddow.gankio.network.ApiBaseComponent;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by yangxiaoguang on 2016/11/4.
 */

public class WelfareFragment extends BaseFragment implements WelfareContact.View {
    @Inject
    WelfareContact.Presenter mPresenter;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private WelfareAdapter mAdapter;
    private List<Welfare> mData = new ArrayList<>();

    public static WelfareFragment newInstance() {

        Bundle args = new Bundle();

        WelfareFragment fragment = new WelfareFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ApiBaseComponent component = ((GankIoApplication) getActivity().getApplication()).getComponent();
        DaggerWelfarePresenterComponent.builder()
                .apiBaseComponent(component)
                .welfarePresenterModule(new WelfarePresenterModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        mAdapter = new WelfareAdapter(R.layout.item_home, mData);
        GridLayoutManager layoutManager = new GridLayoutManager(mActivity, 2);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
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
    public void showData(List<Welfare> data) {
        mData.clear();
        mData.addAll(data);
        mAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void addData(final List<Welfare> results) {
        mAdapter.notifyDataChangedAfterLoadMore(results, true);
    }

    @Override
    public void setPresenter(WelfareContact.Presenter presenter) {
    }
}
