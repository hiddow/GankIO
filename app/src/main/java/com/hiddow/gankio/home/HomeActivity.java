package com.hiddow.gankio.home;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hiddow.gankio.GankIoApplication;
import com.hiddow.gankio.R;
import com.hiddow.gankio.base.BaseActivity;
import com.hiddow.gankio.network.ApiBaseComponent;
import com.hiddow.gankio.util.ActivityUtils;

import javax.inject.Inject;

/**
 * Created by yangxiaoguang on 2016/11/4.
 */

public class HomeActivity extends BaseActivity {
    @Inject
    HomePresenter homePresenter;

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        ApiBaseComponent component = ((GankIoApplication) getApplication()).getComponent();

        HomeFragment homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentById(R.id.frame_layout);
        if (homeFragment == null) {
            homeFragment = HomeFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), homeFragment, R.id.frame_layout);
        }

        DaggerHomePresenterComponent.builder()
                .homePresenterModule(new HomePresenterModule(homeFragment))
                .apiBaseComponent(component)
                .build()
                .inject(this);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_home;
    }
}
