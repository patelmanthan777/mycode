package com.example.administrator.myapplication.archite.lifecicle;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import static android.arch.lifecycle.Lifecycle.State.STARTED;

/**
 * Created by xcy on 2018/2/11 0011.
 */

public class TestClass extends AppCompatTextView implements LifecycleObserver {
    private final String TAG = "绑定生命周期";

    private boolean enable;
    private Lifecycle lifecycle;

    private StringBuffer buf;

    public TestClass(Context context, AttributeSet attrs) {
        super(context, attrs);
        buf = new StringBuffer();
    }

    public void setLifecycle(Lifecycle lifecycle) {
        this.lifecycle = lifecycle;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void create() {
        if (enable) {
            buf.append(System.currentTimeMillis() + "-creat\n");
            this.setText(buf);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void start() {
        if (enable) {
            buf.append(System.currentTimeMillis() + "-start\n");
            this.setText(buf);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void resume() {
        if (enable) {
            buf.append(System.currentTimeMillis() + "-resume\n");
            this.setText(buf);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void pause() {
        if (enable) {
            buf.append(System.currentTimeMillis() + "-pause\n");
            this.setText(buf);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void stop() {
        if (enable) {
            buf.append(System.currentTimeMillis() + "-stop\n");
            this.setText(buf);
        }
    }

    public void setLifecycleEanable(boolean b) {
        enable = b;

        if (lifecycle.getCurrentState().isAtLeast(STARTED)) {
            //在这里做一些逻辑判断
        }
    }
    public void enable() {
        enable = true;
        if (lifecycle.getCurrentState().isAtLeast(STARTED)) {
            // connect if not connected
        }
    }
}