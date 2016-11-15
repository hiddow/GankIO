package com.hiddow.gankio.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hiddow.gankio.R;
import com.hiddow.gankio.base.BaseActivity;
import com.hiddow.gankio.model.object.Welfare;

import butterknife.BindView;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by yangxiaoguang on 2016/11/15.
 */

public class WelfareActivity extends BaseActivity {
    public static final String EXTRA_OBJECT = "extra_object";
    @BindView(R.id.imageView)
    ImageView mImageView;

    private Welfare welfare;

    public static Intent newIntent(Context context, Welfare welfare) {
        Intent intent = new Intent(context, WelfareActivity.class);
        intent.putExtra(EXTRA_OBJECT, welfare);
        return intent;
    }

    public void parseIntent() {
        checkNotNull(getIntent().getSerializableExtra(EXTRA_OBJECT));
        welfare = (Welfare) getIntent().getSerializableExtra(EXTRA_OBJECT);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        parseIntent();
        Glide.with(this)
                .load(welfare.url)
                .asBitmap()
                .into(mImageView);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_welfare;
    }
}
