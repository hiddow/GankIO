package com.hiddow.gankio.home.ios;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hiddow.gankio.R;
import com.hiddow.gankio.model.object.IOSInfo;

import java.util.List;

/**
 * Created by yangxiaoguang on 2016/11/10.
 */

public class IOSInfoAdapter extends BaseQuickAdapter<IOSInfo> {
    public IOSInfoAdapter(int layoutResId, List<IOSInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, IOSInfo iosInfo) {
        baseViewHolder.setText(R.id.text_android_info, iosInfo.desc);
    }
}