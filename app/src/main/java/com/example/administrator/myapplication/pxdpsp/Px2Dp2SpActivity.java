package com.example.administrator.myapplication.pxdpsp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.administrator.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xcy on 2017/4/20 0020.
 */

public class Px2Dp2SpActivity extends Activity {
    @BindView(R.id.btn)
    Button mBtn;
    @BindView(R.id.btn1)
    Button mBtn1;
    @BindView(R.id.btn2)
    Button mBtn2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pxdpsp);
        ButterKnife.bind(this);

    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    @OnClick({R.id.btn, R.id.btn1, R.id.btn2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn:
                Log.e("asdasd", "" + dip2px(this, 100));
                break;
            case R.id.btn1:
                Log.e("asdasd", "" + px2dip(this, 100));
                break;
            case R.id.btn2:
                break;
        }
    }
}
