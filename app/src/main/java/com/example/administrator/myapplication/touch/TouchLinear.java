package com.example.administrator.myapplication.touch;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by xcy on 2017/3/22 0022.
 */

public class TouchLinear extends LinearLayout {
    public TouchLinear(Context context) {
        super(context);
    }

    public TouchLinear(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("asdasd", "TouchLinear" + "|dispatchTouchEvent" + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e("asdasd", "TouchLinear" + "|onInterceptTouchEvent" + ev.getAction());
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("asdasd", "TouchLinear" + "|onTouchEvent" + event.getAction());
        return super.onTouchEvent(event);
    }
}
