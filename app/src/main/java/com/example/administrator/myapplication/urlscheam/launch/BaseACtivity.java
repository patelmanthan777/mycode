package com.example.administrator.myapplication.urlscheam.launch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.administrator.myapplication.R;

import butterknife.ButterKnife;

/**
 * Created by xcy on 2017/6/26 0026.
 */

public class BaseACtivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alpha);
        ButterKnife.bind(this);
        Log.e("asdasd", getTaskId() + "|" + toString());
    }

    public void startAc(Class cla) {
        Intent intent1 = new Intent(this, cla);
        startActivity(intent1);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("asdasd", "onreseume" + toString());
    }

    @Override
    protected void onDestroy() {
        Log.e("asdasd", "onDestroy" + toString());
        super.onDestroy();
    }
}
