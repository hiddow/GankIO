package com.hiddow.gankio.home.bottomView;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;

import com.android.databinding.library.baseAdapters.BR;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hiddow.gankio.model.object.SearchResult;

import java.util.List;

/**
 * Created by yangxiaoguang on 2016/11/11.
 */

public class SearViewAdapter extends BaseQuickAdapter<SearchResult> {
    public SearViewAdapter(int layoutResId, List<SearchResult> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, SearchResult welfare) {
        ViewDataBinding dataBinding = DataBindingUtil.bind(baseViewHolder.itemView);
        dataBinding.setVariable(BR.baseResult, welfare);
        dataBinding.executePendingBindings();
    }
}
