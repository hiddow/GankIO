package com.hiddow.gankio.home.ios;

import com.hiddow.gankio.BasePresenter;
import com.hiddow.gankio.BaseView;
import com.hiddow.gankio.model.object.IOSInfo;

import java.util.List;

/**
 * Created by yangxiaoguang on 2016/11/4.
 */

public interface IOSContact {
    interface Presenter extends BasePresenter {
        void fetchData();
        void loadMore();
        void porformItemClick(IOSInfo item);
    }

    interface View extends BaseView<Presenter> {
        void showData(List<IOSInfo> data);
        void addData(List<IOSInfo> results);
    }

}
