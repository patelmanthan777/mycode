package com.example.administrator.myapplication.broadcast;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xcy on 2017/5/26 0026.
 */

public class MyTestActivity1 extends Activity {
    private String nihaoa;
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
        setContentView(R.layout.activity_broad_cast);
        ButterKnife.bind(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("mytest");
        registerReceiver(new MYBroad1(), intentFilter);
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                startActivity(new Intent(this, BroadCastActivity.class));
                break;
            case R.id.btn2:
                break;
            case R.id.btn3:
                break;
            case R.id.btn4:
                break;
        }
    }

    public class MYBroad1 extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            nihaoa = intent.getStringExtra("intent");
            Log.e("asdasd", nihaoa + "MYBroad1111" + context.toString() + "||" + intent.toString()
            );
        }
    }
}
