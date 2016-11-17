package com.hiddow.gankio;

import android.app.Application;

import com.hiddow.gankio.network.ApiBaseComponent;
import com.hiddow.gankio.network.ApiBaseModule;
import com.hiddow.gankio.network.DaggerApiBaseComponent;
import com.umeng.analytics.MobclickAgent;


/**
 * Created by yangxiaoguang on 2016/11/4.
 */

public class GankIoApplication extends Application {
    ApiBaseComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        MobclickAgent.openActivityDurationTrack(false);
        component = DaggerApiBaseComponent.builder()
                .apiBaseModule(new ApiBaseModule())
                .build();
    }

    public ApiBaseComponent getComponent() {
        return component;
    }
}
