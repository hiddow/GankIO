package com.hiddow.gankio.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.hiddow.gankio.R;
import com.hiddow.gankio.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by yangxiaoguang on 2016/11/11.
 */

public class GithubInfoActivity extends BaseActivity {
    @BindView(R.id.web_view)
    WebView mWebView;
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;
    private String mUrl, mTitle;
    private static final String EXTRA_URL = "extra_url";
    private static final String EXTRA_TITLE = "extra_title";

    public static Intent newIntent(Context context, String extraURL, String extraTitle) {
        Intent intent = new Intent(context, GithubInfoActivity.class);
        intent.putExtra(EXTRA_URL, extraURL);
        intent.putExtra(EXTRA_TITLE, extraTitle);
        return intent;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mUrl = getIntent().getStringExtra(EXTRA_URL);
        mTitle = getIntent().getStringExtra(EXTRA_TITLE);

        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setAppCacheEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        mWebView.setWebChromeClient(new ChromeClient());
        mWebView.setWebViewClient(new WebViewClient());

        mWebView.loadUrl(mUrl);

    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_github_info;
    }

    private class ChromeClient extends WebChromeClient {

        @Override public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            mProgressBar.setProgress(newProgress);
            if (newProgress == 100) {
                mProgressBar.setVisibility(View.GONE);
            } else {
                mProgressBar.setVisibility(View.VISIBLE);
            }
        }


        @Override public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
        }
    }

}
