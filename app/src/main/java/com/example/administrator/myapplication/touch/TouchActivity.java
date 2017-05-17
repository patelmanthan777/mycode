package com.example.administrator.myapplication.touch;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.widget.Button;

import com.example.administrator.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xcy on 2017/3/22 0022.
 */

public class TouchActivity extends Activity {
    @BindView(R.id.btn1)
    Button mBtn1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch);
        ButterKnife.bind(this);
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

    @OnClick(R.id.btn1)
    public void onViewClicked() {
        SparseArray<String> stringSparseArray = new SparseArray<>();
        stringSparseArray.put(3, "你好");
        for (int i = 0; i < stringSparseArray.size(); i++) {
            System.out.println(stringSparseArray.get(i, "无"));
        }
    }
}
