package com.example.administrator.myapplication.urlscheam.launch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.myapplication.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by xcy on 2017/6/26 0026.
 */

public class SingleTaskActivity extends BaseACtivity {
    @BindView(R.id.textview)
    TextView mTextview;
    @BindView(R.id.linear)
    LinearLayout mLinear;
    @BindView(R.id.btn1)
    Button mBtn1;
    @BindView(R.id.btn2)
    Button mBtn2;
    @BindView(R.id.btn3)
    Button mBtn3;
    @BindView(R.id.btn4)
    Button mBtn4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTextview.setText("SingleTaskActivity");
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                startAc(StandardActivityCCC.class);
                break;
            case R.id.btn2:
                startAc(SingleTaskActivity.class);
                break;
            case R.id.btn3:
                startAc(SingleInctanceActivity.class);
                break;
            case R.id.btn4:
                startAc(SingleInctanceActivity1.class);
                break;
        }
    }
}
