package com.example.administrator.myapplication;

import android.os.StrictMode;
import android.support.multidex.MultiDexApplication;


/**
 * Created by xcy on 2017/6/6 0006.
 */

public class MyApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    private static void enabledStrictMode() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder() //
                .detectAll() //
                .penaltyLog() //
                .penaltyDeath() //
                .build());
    }
}
