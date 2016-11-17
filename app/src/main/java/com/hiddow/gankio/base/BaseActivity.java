package com.hiddow.gankio.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.orhanobut.logger.Logger;
import com.umeng.analytics.MobclickAgent;

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

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(getClass().getSimpleName());
        MobclickAgent.onResume(this);
        Logger.d("onResume" + "/" + getClass().getSimpleName());
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(getClass().getSimpleName());
        MobclickAgent.onPause(this);
    }


}
