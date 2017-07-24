package com.example.administrator.myapplication.service_process;

import android.app.ActivityManager;
import android.content.Context;

/**
 * Created by xcy on 2017/5/26 0026.
 */

public class ProcessUtil {
    public static String getProcessName(Context context) {
        int pid = android.os.Process.myPid();
        String processName = "";
        ActivityManager manager = (ActivityManager) context.getSystemService(Context
                .ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo process : manager.getRunningAppProcesses()) {
            if (process.pid == pid) {
                processName = process.processName;
            }
        }
        
        return processName;
    }
}
