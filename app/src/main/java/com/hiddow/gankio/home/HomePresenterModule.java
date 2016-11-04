package com.hiddow.gankio.home;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yangxiaoguang on 2016/11/4.
 */
@Module
public class HomePresenterModule {

    private final HomeContact.View mView;


    HomePresenterModule(HomeContact.View view) {
        mView = view;
    }

    @Provides
    HomeContact.View provideHomeContractView() {
        return mView;
    }

}
