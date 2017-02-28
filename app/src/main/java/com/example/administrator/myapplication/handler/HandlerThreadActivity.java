package com.example.administrator.myapplication.handler;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.util.LruCache;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.myapplication.R;

/**
 * Created by xcy on 2017/1/18 0018.
 */

public class HandlerThreadActivity extends AppCompatActivity {

    private TextView mTvServiceInfo;

    private HandlerThread mCheckMsgThread;
    private Handler mCheckMsgHandler;
    private boolean isUpdateInfo;

    private static final int MSG_UPDATE_INFO = 0x110;

    //与UI线程管理的handler
    private Handler mHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_handler);

        //创建后台线程
        initBackThread1();

        mTvServiceInfo = (TextView) findViewById(R.id.id_textview);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mCheckMsgHandler.sendEmptyMessage(MSG_UPDATE_INFO);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        new Handler().post(new Runnable() {
                            @Override
                            public void run() {
                                Log.e("asdasd", Thread.currentThread().getName());
                            }
                        });
                        Looper.loop();
                    }
                }).start();

                LruCache mLruCache = new LruCache<String, Bitmap>(123) {
                    @Override
                    protected int sizeOf(String key, Bitmap value) {
                        return value.getRowBytes() * value.getHeight();
                    }

                    ;
                };

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //开始查询
        isUpdateInfo = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        //停止查询
        isUpdateInfo = false;
        mCheckMsgHandler.removeMessages(MSG_UPDATE_INFO);

    }

    private void initBackThread() {
        mCheckMsgThread = new HandlerThread("check-message-coming");
        mCheckMsgThread.start();
        mCheckMsgHandler = new Handler(mCheckMsgThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                checkForUpdate();
            }
        };

    }

    private void initBackThread1() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                mCheckMsgHandler = new Handler(Looper.myLooper()) {
                    @Override
                    public void handleMessage(Message msg) {
                        checkForUpdate();
                    }
                };
                Looper.loop();
            }
        }).start();
    }

    /**
     * 模拟从服务器解析数据
     */
    private void checkForUpdate() {
        try {
            //模拟耗时
            Thread.sleep(1000);
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    String result = "实时更新中，当前大盘指数：<font color='red'>%d</font>";
                    result = String.format(result, (int) (Math.random() * 3000 + 1000));
                    mTvServiceInfo.setText(Html.fromHtml(result));
                }
            });

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //释放资源
        mCheckMsgThread.quit();
    }


}