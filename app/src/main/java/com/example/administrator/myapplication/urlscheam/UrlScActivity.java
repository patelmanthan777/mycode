package com.example.administrator.myapplication.urlscheam;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.myapplication.R;

/**
 * Created by xcy on 2017/6/13 0013.
 */

public class UrlScActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alpha);
        Toast.makeText(this, "恭喜", Toast.LENGTH_SHORT).show();
        Uri uri = getIntent().getData();
        if (uri == null) {
            return;
        }
        Log.e("asdasd", uri.toString());
    }
}
