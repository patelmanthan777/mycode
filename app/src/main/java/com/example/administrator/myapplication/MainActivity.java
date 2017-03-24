package com.example.administrator.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.administrator.myapplication.behavior.BehaviorActivity;
import com.example.administrator.myapplication.courese_detail.CourseDetailActivity;
import com.example.administrator.myapplication.handler.HandlerThreadActivity;
import com.example.administrator.myapplication.recycle_virtural.VirturalRecycleActivity;
import com.example.administrator.myapplication.recycler_swipe.MenuHorizontalActivity;
import com.example.administrator.myapplication.touch.TouchActivity;
import com.example.administrator.myapplication.video.VideoMainActivity;
import com.example.administrator.myapplication.video.test_videoplayer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.btn1)
    Button mBtn1;
    @BindView(R.id.btn2)
    Button mBtn2;
    @BindView(R.id.btn3)
    Button mBtn3;
    @BindView(R.id.btn4)
    Button mBtn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_total);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id
            .btn8, R.id.btn9, R.id.btn10})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                //截图
                goNextIntent(com.example.administrator.myapplication.jieping.MainActivity.class);
                break;
            case R.id.btn2:
                //视频+弹幕
                goNextIntent(VideoMainActivity.class);
                break;
            case R.id.btn3:
                goNextIntent(test_videoplayer.class);
                break;
            case R.id.btn4:
                break;
            case R.id.btn5:
                goNextIntent(HandlerThreadActivity.class);
                break;
            case R.id.btn6:
                goNextIntent(BehaviorActivity.class);
                break;
            case R.id.btn7:
                goNextIntent(CourseDetailActivity.class);
                break;
            case R.id.btn8:
                goNextIntent(VirturalRecycleActivity.class);
                break;
            case R.id.btn9:
                goNextIntent(TouchActivity.class);
                break;
            case R.id.btn10:
                goNextIntent(MenuHorizontalActivity.class);
                break;
        }
    }

    public void goNextIntent(Class cla) {
        Intent intent = new Intent(this, cla);
        startActivity(intent);
    }
}
