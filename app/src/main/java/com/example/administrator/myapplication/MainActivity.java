package com.example.administrator.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.administrator.myapplication.alpha.AlphaActivity;
import com.example.administrator.myapplication.behavior.BehaviorActivity;
import com.example.administrator.myapplication.broadcast.BroadCastActivity;
import com.example.administrator.myapplication.bundle.BundleActivity;
import com.example.administrator.myapplication.courese_detail.CourseDetailActivity;
import com.example.administrator.myapplication.gif.GifActivity;
import com.example.administrator.myapplication.handler.HandlerThreadActivity;
import com.example.administrator.myapplication.kuaidi.KuaiDiActivity;
import com.example.administrator.myapplication.permission.PermissionActivity;
import com.example.administrator.myapplication.pxdpsp.Px2Dp2SpActivity;
import com.example.administrator.myapplication.recycle_virtural.VirturalRecycleActivity;
import com.example.administrator.myapplication.recycler_swipe.MenuHorizontalActivity;
import com.example.administrator.myapplication.service_process.mutiprocess.MutiProcessActivity;
import com.example.administrator.myapplication.touch.TouchActivity;
import com.example.administrator.myapplication.urlscheam.launch.StandardActivity;
import com.facebook.drawee.backends.pipeline.Fresco;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_total);
        Fresco.initialize(getApplication());
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id
            .btn8, R.id.btn9, R.id.btn10, R.id.btn11, R.id.btn12, R.id.btn13, R.id.btn14, R.id
            .btn15, R.id.btn16, R.id.btn17, R.id.btn18, R.id.btn19, R.id.btn20, R.id.btn21, R.id
            .btn22})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                //截图
                goNextIntent(com.example.administrator.myapplication.jieping.MainActivity.class);
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
            case R.id.btn11:
                goNextIntent(KuaiDiActivity.class);
                break;
            case R.id.btn12:
                goNextIntent(MutiProcessActivity.class);
                break;
            case R.id.btn13:
                goNextIntent(Px2Dp2SpActivity.class);
                break;
            case R.id.btn14:
                Bundle bundle1 = new Bundle();
                bundle1.putString("aaa", "asdasf");
                Bundle bundle2 = new Bundle();
                bundle2.putString("asfa", "xxxx");
                Intent intent = new Intent(this, BundleActivity.class);
                intent.putExtra("nihaoa", bundle1);
                intent.putExtras(bundle2);
                startActivity(intent);
                break;
            case R.id.btn15:
                startActivity(new Intent(this, AlphaActivity.class));
                break;
            case R.id.btn16:
                startActivity(new Intent(this, GifActivity.class));
                break;
            case R.id.btn17:
                startActivity(new Intent(this, BroadCastActivity.class));
                break;
            case R.id.btn18:
                goNextIntent(PermissionActivity.class);
                break;
            case R.id.btn19:
                goNextIntent(LeakActivity.class);
                break;
            case R.id.btn20:
                Intent intent1 = new Intent();
                Uri uri = Uri.parse("xxx://test/aaa?abc=13");
                intent1.setData(uri);
                startActivity(intent1);
                break;
            case R.id.btn21:
                goNextIntent(StandardActivity.class);
                break;
            case R.id.btn22:
                break;
        }
    }

    public void goNextIntent(Class cla) {
        Intent intent = new Intent(this, cla);
        startActivity(intent);
    }
}
