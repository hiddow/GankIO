package com.hiddow.gankio.home.ios;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yangxiaoguang on 2016/11/4.
 */
@Module
public class IOSPresenterModule {

    private final IOSContact.View mView;


    public IOSPresenterModule(IOSContact.View view) {
        mView = view;
    }

    @Provides
    IOSContact.View provideIOSContractView() {
        return mView;
    }

}
