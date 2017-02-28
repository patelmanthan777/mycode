package com.example.administrator.myapplication.mvvm.method;

import android.databinding.BindingAdapter;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by xcy on 2017/1/6 0006.
 */
public class TestMethod {
    @BindingAdapter({"wenzi"})
    public static void loadImage(TextView view, String text, String xxx) {
        view.setText(text + "||" + xxx);
    }

    @BindingAdapter({"b", "a", "c"})
    public static void loadImage123(ImageView view, String url, String error, String xx) {
        Log.e("asdasd", url + "|||" + error + "|||||" + xx);
    }
}
