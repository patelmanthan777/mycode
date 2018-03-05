package com.example.administrator.myapplication.archite;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.myapplication.R;

/**
 * Created by xcy on 2017/12/8 0008.
 */

public class UserInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        Fragment fragment = new UserInfoFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.framelayout, fragment).commit();
    }
}
