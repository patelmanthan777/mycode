package com.example.administrator.myapplication.mutiprocess;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by xcy on 2017/3/29 0029.
 */

public class ProcessServiceTest1 extends Service {
    String tag = "ysm";

    @Override
    public void onCreate() {
        Log.i(tag, "MyService is oncreate");
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

    class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    Toast.makeText(getApplicationContext(), "成功了", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    ProcessBean re = (ProcessBean) msg.obj;
                    Toast.makeText(getApplicationContext(), re.getName(), Toast.LENGTH_SHORT)
                            .show();
                    break;
                default:
                    Toast.makeText(getApplicationContext(), "可以", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    Messenger mMessenger = new Messenger(new IncomingHandler());
}
