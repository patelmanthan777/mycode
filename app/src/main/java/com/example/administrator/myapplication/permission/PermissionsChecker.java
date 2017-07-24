package com.example.administrator.myapplication.permission;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;


/**
 * 检查权限的工具类
 * <p/>
 * Created by wangchenlong on 16/1/26.
 */
public class PermissionsChecker {
    private static PermissionsChecker mManager;

    public PermissionsChecker() {
    }

    public static PermissionsChecker getInstance() {
        if (mManager == null) {
            mManager = new PermissionsChecker();
        }
        return mManager;
    }

    @TargetApi(value = Build.VERSION_CODES.M)
    public boolean needPermission(Activity activity, String... permisions) {
        boolean needPer = false;
        for (String permision : permisions) {
            if (ActivityCompat.checkSelfPermission(activity, permision)
                    == PackageManager.PERMISSION_DENIED) {
                //未注册
                activity.requestPermissions(new String[]{permision}, 11);
                needPer = true;
            }
        }
        return needPer;
    }

    @TargetApi(value = Build.VERSION_CODES.M)
    public boolean needPermission(Fragment fragment, String... permisions) {
        for (String permision : permisions) {
            if (ContextCompat.checkSelfPermission(fragment.getContext(), permision)
                    == PackageManager.PERMISSION_DENIED) {
                //未注册
                fragment.requestPermissions(new String[]{permision}, 11);
                return true;
            }
        }
        return false;
    }

    @TargetApi(value = Build.VERSION_CODES.M)
    public boolean needPermission(Context context, String... permissions) {
        for (String permision : permissions) {
            if (ContextCompat.checkSelfPermission(context, permision)
                    == PackageManager.PERMISSION_DENIED) {
                //未注册
//                Application.Per(new String[]{permision}, Constants
//                        .MY_PERMISSIONS_REQUEST_CODE);
                return true;
            }
        }
        return false;
    }


}
