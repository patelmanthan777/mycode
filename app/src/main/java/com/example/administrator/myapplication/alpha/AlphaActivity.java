package com.example.administrator.myapplication.alpha;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xcy on 2017/5/11 0011.
 */

public class AlphaActivity extends Activity {
    @BindView(R.id.linear)
    LinearLayout mLinear;
    @BindView(R.id.textview)
    TextView mTextView;
    @BindView(R.id.btn1)
    Button mBtn1;
    @BindView(R.id.btn2)
    Button mBtn2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alpha);
        ButterKnife.bind(this);
    }

    float alpha = 0.0f;
    float alphaL = 0.0f;
    int bigalpha = 0;
    int bigalphaL = 0;

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                alpha += 0.1;
                bigalpha += 10;
//                mTextView.setAlpha(alpha);
                mTextView.getBackground().setAlpha(bigalpha);
                break;
            case R.id.btn2:
                alpha -= 0.1;
                bigalpha -= 10;
                mTextView.getBackground().setAlpha(bigalpha);
//                mTextView.setAlpha(alpha);
                break;
            case R.id.btn3:
                alphaL += 0.1;
                bigalphaL += 10;
//                mLinear.setAlpha(alphaL);
                mLinear.getBackground().setAlpha(bigalphaL);
                break;
            case R.id.btn4:
                alphaL -= 0.1;
                bigalphaL -= 10;
//                mLinear.setAlpha(alphaL);
                mLinear.getBackground().setAlpha(bigalphaL);
                break;
        }
        if (bigalphaL >= 255) {
            bigalphaL = 255;
        }
        if (bigalphaL <= 0) {
            bigalphaL = 0;
        }
        if (bigalpha >= 255) {
            bigalpha = 255;
        }
        if (bigalpha <= 0) {
            bigalpha = 0;
        }
        if (alpha >= 1) {
            alpha = 1;
        }
        if (alpha <= 0) {
            alpha = 0;
        }
        if (alphaL >= 1) {
            alphaL = 1;
        }
        if (alphaL <= 0) {
            alphaL = 0;
        }
    }
}
