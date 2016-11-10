package com.hiddow.gankio.home.welfare;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by yangxiaoguang on 2016/11/4.
 */
@Module
public class WelfarePresenterModule {

    private final WelfareContact.View mView;


    public WelfarePresenterModule(WelfareContact.View view) {
        mView = view;
    }

    @Provides
    WelfareContact.View provideWelfareContractView() {
        return mView;
    }

    @Provides
    WelfareContact.Presenter provideWelfareContractPresenter(Retrofit retrofit, WelfareContact.View view) {
        return new WelfarePresenter(retrofit, view);
    }

}
