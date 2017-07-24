package com.example.administrator.myapplication.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by xcy on 2017/5/26 0026.
 */

public class MyBroadTest extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("asdasd", "MyBroadTest");
    }
}
