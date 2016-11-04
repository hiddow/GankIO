package com.hiddow.gankio.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by yangxiaoguang on 2016/11/4.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        ButterKnife.bind(this);
        initData(savedInstanceState);
    }

    public abstract void initData(@Nullable Bundle savedInstanceState);

    public abstract int getLayoutResource();

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
