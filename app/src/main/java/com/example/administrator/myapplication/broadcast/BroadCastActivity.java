package com.example.administrator.myapplication.broadcast;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

public class BroadCastActivity extends Activity {
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
    }

    int i = 0;

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                break;
            case R.id.btn2:
                startActivity(new Intent(this, MyTestActivity.class));
                break;
            case R.id.btn3:
                MyBroadTest broad = new MyBroadTest();
                IntentFilter filter = new IntentFilter();
                filter.addAction("wocao");
                registerReceiver(broad, filter);
                break;
            case R.id.btn4:
                Intent intent2 = new Intent("mytest");
                ++i;
                intent2.putExtra("intent", "hello receiver." + i);
                sendBroadcast(intent2);
                break;
        }
    }
}
