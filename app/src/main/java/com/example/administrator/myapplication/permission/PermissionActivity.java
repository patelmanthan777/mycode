package com.example.administrator.myapplication.permission;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.example.administrator.myapplication.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xcy on 2017/5/31 0031.
 */

public class PermissionActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                PermissionsChecker.getInstance().needPermission(this, Manifest.permission
                        .WRITE_EXTERNAL_STORAGE, Manifest.permission.CALL_PHONE, Manifest
                        .permission.READ_EXTERNAL_STORAGE);
                break;
            case R.id.btn2:
                break;
            case R.id.btn3:
                break;
            case R.id.btn4:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == 11) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                for (String per : permissions) {
                    Log.e("asdasd", per + ":注册成功");
                }
            } else if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                for (String per : permissions) {
                    Log.e("asdasd", per + ":注册失败");
                }
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
