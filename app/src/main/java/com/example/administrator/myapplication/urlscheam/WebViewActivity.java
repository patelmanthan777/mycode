package com.example.administrator.myapplication.urlscheam;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.example.administrator.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xcy on 2017/7/20 0020.
 */

public class WebViewActivity extends Activity {
    @BindView(R.id.webview)
    WebView mWebview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ButterKnife.bind(this);
        mWebview.loadUrl("file:///android_asset/test2.html");
    }
}
