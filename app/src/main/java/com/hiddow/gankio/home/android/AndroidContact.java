package com.hiddow.gankio.home.android;

import com.hiddow.gankio.BasePresenter;
import com.hiddow.gankio.BaseView;
import com.hiddow.gankio.model.object.AndroidInfo;

import java.util.List;

/**
 * Created by yangxiaoguang on 2016/11/4.
 */

public interface AndroidContact {
    interface Presenter extends BasePresenter {
        void fetchData();
        void loadMore();
    }

    interface View extends BaseView<Presenter> {
        void showData(List<AndroidInfo> data);
        void addData(List<AndroidInfo> results);
    }

}
