package com.hiddow.gankio.home;

import com.hiddow.gankio.BasePresenter;
import com.hiddow.gankio.BaseView;
import com.hiddow.gankio.model.object.Welfare;

import java.util.List;

/**
 * Created by yangxiaoguang on 2016/11/4.
 */

public interface HomeContact {
    interface Presenter extends BasePresenter {
        void fetchData();
        void loadMore();
    }

    interface View extends BaseView<Presenter> {
        void showWelfare(List<Welfare> data);
        void addData(List<Welfare> results);
    }

}
