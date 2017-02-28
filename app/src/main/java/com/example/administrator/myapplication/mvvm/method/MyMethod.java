package com.example.administrator.myapplication.mvvm.method;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * Created by xcy on 2017/1/6 0006.
 */
public class MyMethod {
    public void moclicl() {
        Log.e("asdasd", "aaaaa");
    }

    public void mOnClick(View view) {
        Toast.makeText(view.getContext(), "aa44a", Toast.LENGTH_SHORT).show();
    }

    public boolean mOnLongClick(View view) {
        Toast.makeText(view.getContext(), "长安了啊", Toast.LENGTH_SHORT).show();
        return false;
    }


}
