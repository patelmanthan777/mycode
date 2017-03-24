package com.example.administrator.myapplication.test;

import android.view.View;

/**
 * Created by xcy on 2017/3/24 0024.
 */

public abstract class A {
    private View.OnClickListener mOnClickListener;

    public void setOnClickListener(View.OnClickListener clickListener) {
        mOnClickListener = clickListener;
    }
}
