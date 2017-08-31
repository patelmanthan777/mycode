package com.example.administrator.myapplication.titlebar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.administrator.myapplication.R;

import butterknife.ButterKnife;


/**
 * Created by xcy on 2017/6/6 0006.
 */

public class TitleBarActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);
        ButterKnife.bind(this);
        StatusBarCompat.translucentStatusBar(this, false);
//        StatusBarCompat.setStatusBarColor(this, Color.RED);

    }

    @SuppressLint("InlinedApi")
    private static final int DEFAULT_IMMERSIVE_FLAGS =
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;

    @SuppressLint("InlinedApi")
    private static final int DIALOG_IMMERSIVE_FLAGS =
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

//    @OnClick({R.id.btn, R.id.btn1})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.btn:
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    addVisibilityFlag(getWindow(), DEFAULT_IMMERSIVE_FLAGS);
//
//                    // Also set the navigation bar and status bar to transparent color. Note that
//                    // this
//                    // doesn't work if android.R.boolean.config_enableTranslucentDecor is false.
//                    getWindow().setNavigationBarColor(0);
//                    getWindow().setStatusBarColor(0);
//                }
//                break;
//            case R.id.btn1:
//                break;
//        }
//    }

    public static void addVisibilityFlag(final Window window, final int flag) {
        WindowManager.LayoutParams attrs = window.getAttributes();
        attrs.systemUiVisibility |= flag;
        window.setAttributes(attrs);
    }


}
