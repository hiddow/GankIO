package com.hiddow.gankio.home.bottomView;

import android.content.Context;

import com.hiddow.gankio.home.GithubInfoActivity;
import com.hiddow.gankio.model.SearchResultData;
import com.hiddow.gankio.model.object.SearchResult;
import com.hiddow.gankio.network.GankApi;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yangxiaoguang on 2016/11/4.
 */

public class SearchViewPresenter implements SearchViewContact.Presenter {

    private SearchViewContact.View mView;

    private Retrofit retrofit;

    private int curPage = 1;

    @Inject
    Context mContext;

    String keyWord,category;

    @Inject
    SearchViewPresenter(Retrofit retrofit, SearchViewContact.View view) {
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
    public void fetchData(String keyWord, String category) {
        this.keyWord = keyWord;
        this.category = category;
        curPage = 1;
        retrofit.create(GankApi.class)
                .getSearchData(keyWord,category,10, curPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SearchResultData>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.d(e.getMessage());
                    }

                    @Override
                    public void onNext(SearchResultData searchResultData) {
                        Logger.d("onNext");
                        if (!searchResultData.error) {
                            mView.showData(searchResultData.results);
                        }
                    }
                });
    }

    @Override
    public void loadMore() {
        curPage++;
        retrofit.create(GankApi.class)
                .getSearchData(keyWord,category,10, curPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SearchResultData>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SearchResultData searchResultData) {
                        if (!searchResultData.error) {
                            mView.addData(searchResultData.results);
                        }
                    }
                });
    }

    @Override
    public void porformItemClick(SearchResult item) {
        mContext.startActivity(GithubInfoActivity.newIntent(mContext, item.url, item.desc));
    }

}
