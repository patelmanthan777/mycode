package com.example.administrator.myapplication.bundle;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.administrator.myapplication.R;

/**
 * Created by xcy on 2017/5/5 0005.
 */

public class BundleActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bundle);
        Bundle bundle = getIntent().getExtras();
        Bundle bundle1 = getIntent().getBundleExtra("nihaoa");
        Log.e("asdasd", "");
        FragmentManager fragmentManager = getFragmentManager();
        BundleFragment fragment = new BundleFragment();
        fragment.setArguments(bundle1);
        fragmentManager.beginTransaction().add(R.id.framelayout, fragment).commit();

    }
}
