package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xcy on 2017/6/13 0013.
 */

public class SplashActivity extends Activity {
    @BindView(R.id.textview)
    TextView mTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alpha);
        ButterKnife.bind(this);
        mTextView.setText("我是引导页，等下就跳转");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.e("asdasd", "zhunbei跳转");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = getIntent();
                String action = intent.getAction();
                if (action.equals(Intent.ACTION_MAIN)) {
                    Intent i = new Intent(SplashActivity.this, MainTitleActivity.class);
                    startActivity(i);
                } else if (action.equals(Intent.ACTION_VIEW)) {
                    Intent i = new Intent(SplashActivity.this, MainTitleActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                }
                finish();
            }
        }).start();
    }
}
