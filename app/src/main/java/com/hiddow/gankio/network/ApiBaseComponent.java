package com.hiddow.gankio.network;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by yangxiaoguang on 2016/11/4.
 */
@Singleton
@Component(modules = {ApiBaseModule.class})
public interface ApiBaseComponent {
    OkHttpClient getHttpClicent();

    Retrofit getRetrofit();

}
