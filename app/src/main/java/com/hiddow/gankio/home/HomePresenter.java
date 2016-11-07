package com.hiddow.gankio.home;

import com.hiddow.gankio.model.WelfareData;
import com.hiddow.gankio.network.GankApi;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yangxiaoguang on 2016/11/4.
 */

public class HomePresenter implements HomeContact.Presenter {

    private HomeContact.View mView;

    private Retrofit retrofit;

    private int curPage = 1;

    @Inject
    HomePresenter(Retrofit retrofit, HomeContact.View view) {
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
                .getPicData(10, curPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WelfareData>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(WelfareData welfareData) {
                        if (!welfareData.error) {
                            mView.showWelfare(welfareData.results);
                        }
                    }
                });
    }

    @Override
    public void loadMore(){
        curPage ++;
        retrofit.create(GankApi.class)
                .getPicData(10, curPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WelfareData>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(WelfareData welfareData) {
                        if (!welfareData.error) {
                            mView.addData(welfareData.results);
                        }
                    }
                });
    }

}
