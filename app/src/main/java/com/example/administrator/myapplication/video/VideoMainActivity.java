package com.example.administrator.myapplication.video;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.video.sample.BiliDanmukuParser;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCUtils;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import master.flame.danmaku.controller.DrawHandler;
import master.flame.danmaku.danmaku.loader.ILoader;
import master.flame.danmaku.danmaku.loader.IllegalDataException;
import master.flame.danmaku.danmaku.loader.android.DanmakuLoaderFactory;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDisplayer;
import master.flame.danmaku.danmaku.model.android.BaseCacheStuffer;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.model.android.SpannedCacheStuffer;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.danmaku.parser.IDataSource;
import master.flame.danmaku.danmaku.util.IOUtils;
import master.flame.danmaku.ui.widget.DanmakuView;

/**
 * Created by xcy on 2017/2/27 0027.
 * 弹幕+视频播放
 */

public class VideoMainActivity extends Activity {

    @BindView(R.id.edittext)
    EditText mEdittext;
    private JCVideoPlayerStandard mVideoPlayer;
    private DanmakuView danmakuView;
    private boolean showDanmaku;
    private DanmakuContext danmakuContext;
    private BaseDanmakuParser mParser;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);
        danmakuContext = DanmakuContext.create();
        mVideoPlayer = (JCVideoPlayerStandard) findViewById(R.id.videoplayer);
        mVideoPlayer.setUp("http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3" +
                ".f20.mp4", JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "这是标题");
        mVideoPlayer.thumbImageView.setImageURI(Uri.parse("http://p.qpic" +
                ".cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640"));
        initDanmu();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
    }

    /**
     * 初始化弹幕
     */
    private void initDanmu() {
        //
        // 设置弹幕的最大显示行数
        HashMap<Integer, Integer> maxLinesPair = new HashMap<>();
        maxLinesPair.put(BaseDanmaku.TYPE_SCROLL_RL, 3); // 滚动弹幕最大显示3行
        // 设置是否禁止重叠
        HashMap<Integer, Boolean> overlappingEnablePair = new HashMap<Integer, Boolean>();
        overlappingEnablePair.put(BaseDanmaku.TYPE_SCROLL_LR, true);
        overlappingEnablePair.put(BaseDanmaku.TYPE_FIX_BOTTOM, true);
        danmakuContext.setDanmakuStyle(IDisplayer.DANMAKU_STYLE_STROKEN, 3) //设置描边样式
                .setDuplicateMergingEnabled(false)
                .setScrollSpeedFactor(1.2f) //是否启用合并重复弹幕
                .setScaleTextSize(1.2f) //设置弹幕滚动速度系数,只对滚动弹幕有效
                .setCacheStuffer(new SpannedCacheStuffer(), mCacheStufferAdapter) //
                // 图文混排使用SpannedCacheStuffer
                // 设置缓存绘制填充器，默认使用{@link SimpleTextCacheStuffer}只支持纯文字显示,
                // 如果需要图文混排请设置{@link SpannedCacheStuffer
                // }如果需要定制其他样式请扩展{@link SimpleTextCacheStuffer}|{@link SpannedCacheStuffer}
                .setMaximumLines(maxLinesPair) //设置最大显示行数
                .preventOverlapping(overlappingEnablePair); //设置防弹幕重叠，null为允许重叠

        mParser = createParser(getResources().openRawResource(R.raw.comments));
        //
        ViewGroup vp = (ViewGroup) (JCUtils.scanForActivity(this))//.getWindow()
                // .getDecorView();
                .findViewById(Window.ID_ANDROID_CONTENT);
        danmakuView = new DanmakuView(this);
        vp.addView(danmakuView);

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
//        danmakuView.setOnDanmakuClickListener(new IDanmakuView.OnDanmakuClickListener() {
//            @Override
//            public boolean onDanmakuClick(IDanmakus danmakus) {
//                Log.d("DFM", "onDanmakuClick: danmakus size:" + danmakus.size());
//                BaseDanmaku latest = danmakus.last();
//                if (null != latest) {
//                    Log.d("DFM", "onDanmakuClick: text of latest danmaku:" + latest.text);
//                    return true;
//                }
//                return false;
//            }
//
//            @Override
//            public boolean onViewClick(IDanmakuView view) {
////                mMediaController.setVisibility(View.VISIBLE);
//                Log.d("DFM", "onViewClick");
//                return false;
//            }
//        });
        danmakuView.showFPS(false);
        danmakuView.enableDanmakuDrawingCache(true);


    }

    private BaseDanmakuParser createParser(InputStream stream) {
        if (stream == null) {
            return new BaseDanmakuParser() {
                @Override
                protected Danmakus parse() {
                    return new Danmakus();
                }
            };
        }
        /*
        * 这里面DanmakuLoaderFactory.create(DanmakuLoaderFactory.TAG_BILI)
        * DanmakuLoaderFactory.create(DanmakuLoaderFactory.TAG_ACFUN)
        * 查看源码可以知道，TAG_BILI里面创建的BiliDanmakuLoader对象，调用load之后，都是将
        * inputsterm读取出来，然后可以通过loader.getDataSource()来获取输入流，
        * TAG_ACFUN：
        * 也是读取到内部inputsterm，不同的是，会把输入流转化到JSONArray对象，
        * */
        ILoader loader = DanmakuLoaderFactory.create(DanmakuLoaderFactory.TAG_BILI);
        try {
            loader.load(stream);
        } catch (IllegalDataException e) {
            e.printStackTrace();
        }
        //以下的解析是需要根据需要自行定义开始解析的
        BaseDanmakuParser parser = new BiliDanmukuParser();
        IDataSource<?> dataSource = loader.getDataSource();
        parser.load(dataSource);
        return parser;
    }

    //    private class BackgroundCacheStuffer extends SpannedCacheStuffer {
//        // 通过扩展SimpleTextCacheStuffer或SpannedCacheStuffer个性化你的弹幕样式
//        final Paint paint = new Paint();
//
//        @Override
//        public void measure(BaseDanmaku danmaku, TextPaint paint, boolean fromWorkerThread) {
//            danmaku.padding = 10;  // 在背景绘制模式下增加padding
//            super.measure(danmaku, paint, fromWorkerThread);
//        }
//
//        @Override
//        public void drawBackground(BaseDanmaku danmaku, Canvas canvas, float left, float top) {
//            paint.setColor(getResources().getColor(R.color.color_531));
//            //弹幕背景颜色
//            canvas.drawRect(left + 2, top + 2, left + danmaku.paintWidth - 2, top + danmaku
//                    .paintHeight - 2, paint);
//        }
//
//        @Override
//        public void drawStroke(BaseDanmaku danmaku, String lineText, Canvas canvas, float left,
//                               float top, Paint paint) {
//            // 禁用描边绘制
//        }
//    }
    private void addDanmaku(String text, boolean islive) {
        BaseDanmaku danmaku = danmakuContext.mDanmakuFactory.createDanmaku(BaseDanmaku
                .TYPE_SCROLL_RL);
        if (danmaku == null || danmakuView == null) {
            return;
        }
        // for(int i=0;i<100;i++){
        // }
        danmaku.text = "这是一条弹幕:" + text + System.nanoTime();
        danmaku.padding = 5;
        danmaku.priority = 0;  // 可能会被各种过滤器过滤并隐藏显示
        danmaku.isLive = islive;
        danmaku.setTime(danmakuView.getCurrentTime() + 1200);
        danmaku.textSize = 25f * (mParser.getDisplayer().getDensity() - 0.6f);
        danmaku.textColor = Color.RED;
        danmaku.textShadowColor = Color.WHITE;
        // danmaku.underlineColor = Color.GREEN;
        danmaku.borderColor = Color.GREEN;
        danmakuView.addDanmaku(danmaku);
    }

    private void addDanmaKuShowTextAndImage(boolean islive) {
        BaseDanmaku danmaku = danmakuContext.mDanmakuFactory.createDanmaku(BaseDanmaku
                .TYPE_SCROLL_RL);
        Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
        drawable.setBounds(0, 0, 100, 100);
        SpannableStringBuilder spannable = createSpannable(drawable);
        danmaku.text = spannable;
        danmaku.padding = 5;
        danmaku.priority = 1;  // 一定会显示, 一般用于本机发送的弹幕
        danmaku.isLive = islive;
        danmaku.setTime(danmakuView.getCurrentTime() + 1200);
        danmaku.textSize = 25f * (mParser.getDisplayer().getDensity() - 0.6f);
        danmaku.textColor = Color.RED;
        danmaku.textShadowColor = 0; // 重要：如果有图文混排，最好不要设置描边(设textShadowColor=0)，否则会进行两次复杂的绘制导致运行效率降低
        danmaku.underlineColor = Color.GREEN;
        danmakuView.addDanmaku(danmaku);
    }

    private SpannableStringBuilder createSpannable(Drawable drawable) {
        String text = "bitmap";
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
        ImageSpan span = new ImageSpan(drawable);//ImageSpan.ALIGN_BOTTOM);
        spannableStringBuilder.setSpan(span, 0, text.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableStringBuilder.append("图文混排");
        spannableStringBuilder.setSpan(new BackgroundColorSpan(Color.parseColor("#8A2233B1")), 0,
                spannableStringBuilder.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        return spannableStringBuilder;
    }

    private BaseCacheStuffer.Proxy mCacheStufferAdapter = new BaseCacheStuffer.Proxy() {
        private Drawable mDrawable;

        /**
         * 在弹幕显示前使用新的text,使用新的text
         * @param danmaku
         * @param fromWorkerThread 是否在工作(非UI)线程,在true的情况下可以做一些耗时操作(例如更新Span的drawblae或者其他IO操作)
         * @return 如果不需重置，直接返回danmaku.text
         */
        @Override
        public void prepareDrawing(final BaseDanmaku danmaku, boolean fromWorkerThread) {
            if (danmaku.text instanceof Spanned) { // 根据你的条件检查是否需要需要更新弹幕
                // FIXME 这里只是简单启个线程来加载远程url图片，请使用你自己的异步线程池，最好加上你的缓存池
                new Thread() {
                    @Override
                    public void run() {
                        String url = "http://www.bilibili.com/favicon.ico";
                        InputStream inputStream = null;
                        Drawable drawable = mDrawable;
                        if (drawable == null) {
                            try {
                                URLConnection urlConnection = new URL(url).openConnection();
                                inputStream = urlConnection.getInputStream();
                                drawable = BitmapDrawable.createFromStream(inputStream, "bitmap");
                                mDrawable = drawable;
                            } catch (IOException e) {
                                e.printStackTrace();
                            } finally {
                                IOUtils.closeQuietly(inputStream);
                            }
                        }
                        if (drawable != null) {
                            drawable.setBounds(0, 0, 100, 100);
                            danmaku.text = createSpannable(drawable);
                            if (danmakuView != null) {
                                danmakuView.invalidateDanmaku(danmaku, false);
                            }
                        }
                    }
                }.start();
            }
        }

        @Override
        public void releaseResource(BaseDanmaku danmaku) {
            // TODO 重要:清理含有ImageSpan的text中的一些占用内存的资源 例如drawable
        }
    };

    /**
     * sp转px的方法。
     */
    public int sp2px(float spValue) {
        final float fontScale = getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (danmakuView != null) {
            // dont forget release!
            danmakuView.release();
            danmakuView = null;
        }
    }

    @OnClick({R.id.btn1, R.id.btn2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                //开始弹幕
//                FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
//                        ViewGroup.LayoutParams.MATCH_PARENT, mVideoPlayer.getMeasuredHeight());
//                lp.setMargins(0, sp2px(48), 0, sp2px(48));
                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) danmakuView
                        .getLayoutParams();
                params.height = mVideoPlayer.getMeasuredHeight();
                params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                params.setMargins(0, sp2px(48), 0, sp2px(48));
                danmakuView.setLayoutParams(params);
                danmakuView.prepare(mParser, danmakuContext);
                break;
            case R.id.btn2:
                //发送弹幕
                addDanmaku(mEdittext.getText().toString(), false);
                break;
        }
    }
}
