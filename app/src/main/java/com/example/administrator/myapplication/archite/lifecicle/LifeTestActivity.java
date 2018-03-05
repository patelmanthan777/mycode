package com.example.administrator.myapplication.archite.lifecicle;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.myapplication.R;

/**
 * Created by xcy on 2018/2/11 0011.
 */

public class LifeTestActivity extends AppCompatActivity implements LifecycleOwner {

    private LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_test);

        TestClass testClass = findViewById(R.id.test_view);
        testClass.setLifecycle(getLifecycle());

        getLifecycle().addObserver(testClass);
        testClass.setLifecycleEanable(true);
    }

    @Override
    public Lifecycle getLifecycle() {
        return lifecycleRegistry;
    }
}