package com.hiddow.gankio.home.android;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yangxiaoguang on 2016/11/4.
 */
@Module
public class AndroidPresenterModule {

    private final AndroidContact.View mView;


    public AndroidPresenterModule(AndroidContact.View view) {
        mView = view;
    }

    @Provides
    AndroidContact.View provideAndroidContractView() {
        return mView;
    }

}
