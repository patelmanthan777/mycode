package com.example.administrator.myapplication.touch;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;

import com.example.administrator.myapplication.R;

/**
 * Created by xcy on 2017/3/22 0022.
 */

public class TouchActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("asdasd", "TouchActivity" + "|dispatchTouchEvent" + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("asdasd", "TouchActivity" + "|onTouchEvent" + event.getAction());
        return super.onTouchEvent(event);
    }
}
