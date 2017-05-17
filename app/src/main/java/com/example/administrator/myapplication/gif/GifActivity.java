package com.example.administrator.myapplication.gif;

import android.app.Activity;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.administrator.myapplication.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xcy on 2017/5/15 0015.
 */

public class GifActivity extends Activity {
    @BindView(R.id.iv)
    SimpleDraweeView mIv;
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

    //

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif);
        ButterKnife.bind(this);
        DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                .setUri(Uri.parse("https://media0.giphy.com/media/oOTTyHRHj0HYY/giphy.gif"))
                .setAutoPlayAnimations(true)
                .setControllerListener(new BaseControllerListener<ImageInfo>() {
                    @Override
                    public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable
                            animatable) {
                        super.onFinalImageSet(id, imageInfo, animatable);
                        Log.e("asdasd", "" + String.valueOf(animatable == null));
                    }
                })
                .build();
        mIv.setController(draweeController);

    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                mIv.getController().getAnimatable().start();
                break;
            case R.id.btn2:
                mIv.getController().getAnimatable().stop();
                break;
            case R.id.btn3:
                break;
            case R.id.btn4:
                break;
        }
    }
}
