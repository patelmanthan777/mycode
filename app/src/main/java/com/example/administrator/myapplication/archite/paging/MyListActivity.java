package com.example.administrator.myapplication.archite.paging;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.myapplication.R;

/**
 * Created by xcy on 2018/3/5 0005.
 */

public class MyListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        getSupportFragmentManager().beginTransaction().add(R.id.framelayout, new MyListFragment()
        ).commit();
    }
}
