package com.hiddow.gankio.home.welfare;

import com.hiddow.gankio.BasePresenter;
import com.hiddow.gankio.BaseView;
import com.hiddow.gankio.model.object.Welfare;

import java.util.List;

/**
 * Created by yangxiaoguang on 2016/11/4.
 */

public interface WelfareContact {
    interface Presenter extends BasePresenter {
        void fetchData();
        void loadMore();
    }

    interface View extends BaseView<Presenter> {
        void showData(List<Welfare> data);
        void addData(List<Welfare> results);
    }

}
