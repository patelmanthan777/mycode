package com.example.administrator.myapplication.touch;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by xcy on 2017/3/22 0022.
 */

public class TouchTextView extends AppCompatTextView {
    public TouchTextView(Context context) {
        super(context);
    }

    public TouchTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("asdasd", "TouchTextView" + "|dispatchTouchEvent" + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("asdasd", "TouchTextView" + "|onTouchEvent" + event.getAction());
        return false;
    }
}
