package com.example.administrator.myapplication.behavior;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.example.administrator.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xcy on 2017/3/14 0014.
 */

public class BehaviorActivity extends Activity {
    @BindView(R.id.swipe)
    Button mSwipe;
    @BindView(R.id.mybehavior)
    Button mMybehavior;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_behavior_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.mybehavior, R.id.swipe, R.id.recycler})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mybehavior:

                break;
            case R.id.swipe:
                break;
            case R.id.recycler:
                startActivity(new Intent(this, RecycleToolbarActivity.class));
                break;
        }
    }
}
