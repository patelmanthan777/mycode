package com.example.administrator.myapplication.kuaidi;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xcy on 2017/3/27 0027.
 */

public class KuaiDiActivity extends Activity {
    @BindView(R.id.text)
    TextView mText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuaidi);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.btn, R.id.btn1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn:
                final KuaiDiApi api = new KuaiDiApi();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String result = api.orderTracesSubByJson();
                            System.out.print(result);
                            Log.e("asdasd", result);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;
            case R.id.btn1:
                break;
        }
    }
}
