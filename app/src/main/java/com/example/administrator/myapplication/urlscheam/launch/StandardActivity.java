package com.example.administrator.myapplication.urlscheam.launch;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by xcy on 2017/6/26 0026.
 */

public class StandardActivity extends BaseACtivity {
    @BindView(R.id.textview)
    TextView mTextview;
    @BindView(R.id.linear)
    LinearLayout mLinear;
    @BindView(R.id.btn1)
    Button mBtn1;
    @BindView(R.id.btn2)
    Button mBtn2;
    @BindView(R.id.btn3)
    Button mBtn3;
    @BindView(R.id.btn4)
    Button mBtn4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTextview.setText("StandardActivity");
        EventBus.getDefault().register(this);
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                Intent intent = new Intent(this, StandardActivityBBB.class);
                startActivity(intent);
                break;
            case R.id.btn2:
                Intent serviceIntent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri
                        .parse("package:" + getPackageName()));
                startActivityForResult(serviceIntent, 1111);
                break;
            case R.id.btn3:
                ProgressDialog dialog = ProgressDialog.show(this, "nihao", "xxxx");
                dialog.setCancelable(true);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent123 = new Intent(StandardActivity.this, StandardActivityBBB
                                .class);
                        startActivity(intent123);
                    }
                }, 1000);

                break;
            case R.id.btn4:
                break;
        }
    }

    @Subscribe
    public void onEvent(WodeEvent event) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.e("asdasd", "onEvent了");
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.e("asdasd", "结束");
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1111 && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Settings.canDrawOverlays(this)) {
                Toast.makeText(this, "Overlay permissions have been granted.", Toast.LENGTH_LONG)
                        .show();
            }
        }
    }
}
