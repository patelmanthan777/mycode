package com.example.administrator.myapplication.video;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;

import com.example.administrator.myapplication.R;

import fm.jiecao.jcvideoplayer_lib.JCUtils;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import master.flame.danmaku.controller.DrawHandler;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.ui.widget.DanmakuView;

/**
 * Created by xcy on 2017/2/27 0027.
 */

public class VideoMainActivity extends Activity {

    private JCVideoPlayerStandard mVideoPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        mVideoPlayer = (JCVideoPlayerStandard) findViewById(R.id.videoplayer);
        mVideoPlayer.setUp("http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3" +
                ".f20.mp4", JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "这是标题");
        mVideoPlayer.thumbImageView.setImageURI(Uri.parse("http://p.qpic" +
                ".cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640"));
    }

    private DanmakuView danmakuView;
    private boolean showDanmaku;
    private DanmakuContext danmakuContext;

    /**
     * 初始化弹幕
     */
    private void initDanmu() {
        ViewGroup vp = (ViewGroup) (JCUtils.scanForActivity(this))//.getWindow()
                // .getDecorView();
                .findViewById(Window.ID_ANDROID_CONTENT);

        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lp.setMargins(0, sp2px(48), 0, sp2px(48));
        danmakuView = new DanmakuView(this);
        vp.addView(danmakuView, lp);
        danmakuView.enableDanmakuDrawingCache(true);
        danmakuView.setCallback(new DrawHandler.Callback() {
            @Override
            public void prepared() {
                showDanmaku = true;
                danmakuView.start();
            }

            @Override
            public void updateTimer(DanmakuTimer timer) {

            }

            @Override
            public void danmakuShown(BaseDanmaku danmaku) {

            }

            @Override
            public void drawingFinished() {

            }
        });
        danmakuContext = DanmakuContext.create();
        danmakuView.prepare(parser, danmakuContext);

    }

    private BaseDanmakuParser parser = new BaseDanmakuParser() {
        @Override
        protected IDanmakus parse() {
            return new Danmakus();
        }
    };

    /**
     * sp转px的方法。
     */
    public int sp2px(float spValue) {
        final float fontScale = getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }


}
