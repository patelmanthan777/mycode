package com.example.administrator.myapplication.service_process.mutiprocess;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by xcy on 2017/3/29 0029.
 */

public class ProcessServiceTest1 extends Service {
    String tag = "ysm";

    @Override
    public void onCreate() {
        Log.i(tag, "MyService is oncreate");
        EventBus.getDefault().register(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(tag, "onStartCommand: " + MutiProcessActivity.iscreated);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.i(tag, "OnDestory");
    }

    @Subscribe
    public void onEvent(ServiceEvent xx) {
        Log.e("asdasd", xx.toString());
    }

    @Override
    public IBinder onBind(Intent arg0) {
        Log.i(tag, "onBind ");
        Toast.makeText(getApplicationContext(), "binding", Toast.LENGTH_SHORT).show();
        return mMessenger.getBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(tag, "onUnbind ");
        return super.onUnbind(intent);
    }

    private static class IncomingHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    Log.e("asdasd", "000");
                    break;
                case 1:
                    Log.e("asdasd", "1111");
//                    ProcessBean re = (ProcessBean) msg.obj;
//                    Toast.makeText(getApplicationContext(), re.getName(), Toast.LENGTH_SHORT)
//                            .show();
                    break;
                default:
                    Log.e("asdasd", "defaultdefault");
                    break;
            }
        }
    }

    Messenger mMessenger = new Messenger(new IncomingHandler());
}
