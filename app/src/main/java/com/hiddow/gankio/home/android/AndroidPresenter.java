package com.hiddow.gankio.home.android;

import com.hiddow.gankio.model.AndroidData;
import com.hiddow.gankio.network.GankApi;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yangxiaoguang on 2016/11/4.
 */

public class AndroidPresenter implements AndroidContact.Presenter {

    private AndroidContact.View mView;

    private Retrofit retrofit;

    private int curPage = 1;

    @Inject
    AndroidPresenter(Retrofit retrofit, AndroidContact.View view) {
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
                .getAndroidData(10, curPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AndroidData>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(AndroidData androidData) {
                        if (!androidData.error) {
                            mView.showData(androidData.results);
                        }
                    }
                });
    }

    @Override
    public void loadMore(){
        curPage ++;
        retrofit.create(GankApi.class)
                .getAndroidData(10, curPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AndroidData>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(AndroidData androidData) {
                        if (!androidData.error) {
                            mView.addData(androidData.results);
                        }
                    }
                });
    }

}
