package com.hiddow.gankio.home.ios;

import com.hiddow.gankio.ApplicationModule;
import com.hiddow.gankio.network.ApiBaseComponent;
import com.hiddow.gankio.util.FragmentScoped;

import dagger.Component;

/**
 * Created by yangxiaoguang on 2016/11/4.
 */
@FragmentScoped
@Component(modules = {IOSPresenterModule.class, ApplicationModule.class}, dependencies = {ApiBaseComponent.class})
public interface IOSPresenterComponent {
    void inject(IOSFragment fragment);
}
