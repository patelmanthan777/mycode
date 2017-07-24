package com.example.administrator.myapplication.service_process.mutiprocess;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.service_process.ProcessUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xcy on 2017/5/26 0026.
 */

public class ProcessActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process_ac);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

    }

    @Subscribe
    public void onEvent(ServiceEvent event) {
        Log.e("asdasd", event.toString());
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                Log.e("asdasd", ProcessUtil.getProcessName(this) + "||" + Process.myPid());
                break;
            case R.id.btn2:
                startActivity(new Intent(this, ProcessActivity1.class));
                break;
            case R.id.btn3:
                Log.e("asdasd", getApplication().toString());
                break;
            case R.id.btn4:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Process.killProcess(Process.myPid());
    }
}
