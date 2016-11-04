package com.hiddow.gankio.home;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hiddow.gankio.R;
import com.hiddow.gankio.model.object.Welfare;

import java.util.List;

/**
 * Created by yangxiaoguang on 2016/11/4.
 */

public class HomeAdapter extends BaseQuickAdapter<Welfare> {


    public HomeAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder baseViewHolder, Welfare o) {
        baseViewHolder.setText(R.id.text_url, o.url);
    }
}
