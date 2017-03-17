package com.example.administrator.myapplication.behavior;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.recyclerview.xrecycleview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xcy on 2017/3/14 0014.
 * recycler+侧滑删除+下拉刷新+behavior
 */

public class RecycleToolbarActivity extends Activity {
    @BindView(R.id.recycle)
    XRecyclerView mRecycle;
    List<String> lists = new ArrayList<>();
    RecyclerView.Adapter adapter;
    @BindView(R.id.toolbar)
    Titlebar mToolbar;
    @BindView(R.id.appbarlayout)
    AppBarLayout mAppbarlayout;
    @BindView(R.id.btn1)
    Button mBtn1;
    @BindView(R.id.btn2)
    Button mBtn2;
    @BindView(R.id.btn3)
    Button mBtn3;
    @BindView(R.id.btn4)
    Button mBtn4;
    @BindView(R.id.btn5)
    Button mBtn5;
    private HandlerThread mHandlerThread;
    private Handler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_behavior_recycle);
        ButterKnife.bind(this);
        mHandlerThread = new HandlerThread("123");
        mHandlerThread.start();
        for (int i = 0; i < 30; i++) {
            lists.add("wolegecai" + i);
        }
        adapter = new RecyclerView.Adapter<MViewHolder>() {
            @Override
            public MViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(RecycleToolbarActivity.this).inflate(R.layout
                        .activity_behavior_swipe, parent, false);
                return new MViewHolder(view);
            }

            @Override
            public void onBindViewHolder(MViewHolder holder, int position) {
                holder.initData(position);
            }

            @Override
            public int getItemCount() {
                return lists.size();
            }
        };
        mRecycle.setLayoutManager(new LinearLayoutManager(this));
        mRecycle.setAdapter(adapter);
        mRecycle.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mHandler.sendMessage(mHandler.obtainMessage());
            }

            @Override
            public void onLoadMore() {
                mHandler.sendMessage(mHandler.obtainMessage());
            }
        });
        mHandler = new Handler(mHandlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                try {
                    Thread.sleep(1000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mRecycle.refreshComplete();
                            mRecycle.loadMoreComplete();
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        mToolbar.setTitle("你好啊");
        mToolbar.setSubtitle("哎呦");
        mToolbar.setNavigationIcon(R.mipmap.ic_launcher);
        ItemTouchHelper.Callback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper
                .LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                Log.e("asdasd", viewHolder.getAdapterPosition() + "||" + viewHolder
                        .getLayoutPosition());
                int posi = viewHolder.getAdapterPosition() - 1;
                lists.remove(posi);
                adapter.notifyItemRemoved(posi + 1);
            }
        };
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(mRecycle);
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5})
    public void onClick(View view) {
        AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) mToolbar.getLayoutParams();
        switch (view.getId()) {
            case R.id.btn1:
                params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL);
                break;
            case R.id.btn2:
                params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout
                        .LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED);
                break;
            case R.id.btn3:
                params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout
                        .LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
                break;
            case R.id.btn4:
                params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
                break;
            case R.id.btn5:
//                params.setScrollFlags(AppBarLayout.LayoutParams.);
                break;
        }
        mToolbar.setLayoutParams(params);
    }

    private class MViewHolder extends RecyclerView.ViewHolder {
        TextView swipeView;

        public MViewHolder(View itemView) {
            super(itemView);
            swipeView = (TextView) itemView.findViewById(R.id.swip);
        }

        public void initData(int position) {
            swipeView.setText(lists.get(position));
        }

    }

}
