package com.hiddow.gankio.home.ios;

import android.content.Context;

import com.hiddow.gankio.home.GithubInfoActivity;
import com.hiddow.gankio.model.IOSData;
import com.hiddow.gankio.model.object.IOSInfo;
import com.hiddow.gankio.network.GankApi;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yangxiaoguang on 2016/11/4.
 */

public class IOSPresenter implements IOSContact.Presenter {

    private IOSContact.View mView;

    private Retrofit retrofit;

    private int curPage = 1;

    @Inject
    Context mContext;

    @Inject
    IOSPresenter(Retrofit retrofit, IOSContact.View view) {
        this.retrofit = retrofit;
        this.mView = view;
    }

    @Inject
    void setupListeners() {
        mView.setPresenter(this);
    }

    @Override
    public void bind() {

    }

    @Override
    public void unBind() {

    }

    @Override
    public void fetchData() {
        curPage = 1;
        retrofit.create(GankApi.class)
                .getIOSData(10, curPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<IOSData>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(IOSData iosData) {
                        if (!iosData.error) {
                            mView.showData(iosData.results);
                        }
                    }
                });
    }

    @Override
    public void loadMore(){
        curPage ++;
        retrofit.create(GankApi.class)
                .getIOSData(10, curPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<IOSData>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(IOSData iosData) {
                        if (!iosData.error) {
                            mView.addData(iosData.results);
                        }
                    }
                });
    }

    @Override
    public void porformItemClick(IOSInfo item) {
        mContext.startActivity(GithubInfoActivity.newIntent(mContext,item.url,item.desc));
    }

}
