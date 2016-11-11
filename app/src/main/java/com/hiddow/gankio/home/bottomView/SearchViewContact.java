package com.hiddow.gankio.home.bottomView;

import com.hiddow.gankio.BasePresenter;
import com.hiddow.gankio.BaseView;
import com.hiddow.gankio.model.object.SearchResult;

import java.util.List;

/**
 * Created by yangxiaoguang on 2016/11/4.
 */

public interface SearchViewContact {
    interface Presenter extends BasePresenter {
        void fetchData(String keyWord, String category);

        void loadMore();

        void porformItemClick(SearchResult item);
    }

    interface View extends BaseView<Presenter> {
        void showData(List<SearchResult> data);

        void addData(List<SearchResult> results);
    }

}
