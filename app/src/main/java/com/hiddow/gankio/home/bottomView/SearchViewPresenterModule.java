package com.hiddow.gankio.home.bottomView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yangxiaoguang on 2016/11/4.
 */
@Module
public class SearchViewPresenterModule {

    private final SearchViewContact.View mView;


    public SearchViewPresenterModule(SearchViewContact.View view) {
        mView = view;
    }

    @Provides
    SearchViewContact.View provideAndroidContractView() {
        return mView;
    }

}
