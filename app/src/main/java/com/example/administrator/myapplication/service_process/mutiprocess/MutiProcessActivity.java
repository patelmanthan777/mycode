package com.example.administrator.myapplication.service_process.mutiprocess;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Process;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.service_process.ProcessUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xcy on 2017/3/29 0029.
 */

public class MutiProcessActivity extends Activity {
    static boolean iscreated = false;
    final static String TAG = "ysm";
    @BindView(R.id.btn1)
    Button mBtn1;
    @BindView(R.id.btn2)
    Button mBtn2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mutiprocess);
        ButterKnife.bind(this);
        iscreated = true;
    }

    private boolean mBound;
    private Messenger mMessenger;
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mMessenger = new Messenger(service);
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mMessenger = null;
            mBound = false;
        }
    };

    public void sayHello() {
        if (!mBound) {
            return;
        }
        Message msg = Message.obtain(null, 0, 0, 0);
//        Message msg = Message.obtain();
        msg.what = 1;
//        ProcessBean bean = new ProcessBean();
//        bean.setName("wolegequ");
//        msg.obj = bean;
        try {
            mMessenger.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mBound) {
            unbindService(mServiceConnection);
            mBound = false;
        }
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                Intent intent1 = new Intent(this, ProcessServiceTest1.class);
                startService(intent1);
                break;
            case R.id.btn2:
                Intent intent = new Intent(this, ProcessServiceTest1.class);
                bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
                break;
            case R.id.btn3:
                sayHello();
                break;
            case R.id.btn4:
                ServiceEvent x = new ServiceEvent();
                x.setNanme("hellow");
                EventBus.getDefault().post(x);
                break;
            case R.id.btn5:
                Log.e("asdasd", ProcessUtil.getProcessName(this) + "||" + Process.myPid());
                Intent intent2 = new Intent(this, ProcessActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
