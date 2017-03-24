package com.example.administrator.myapplication.recycle_virtural;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.example.administrator.myapplication.R;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xcy on 2017/3/21 0021.
 */

public class VirturalRecycleActivity extends Activity {
    @BindView(R.id.recycleview)
    RecyclerView mRecycleview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_virtual);
        ButterKnife.bind(this);

        VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        mRecycleview.setLayoutManager(layoutManager);
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        mRecycleview.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 10);

        DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager);
        mRecycleview.setAdapter(delegateAdapter);
        LinearLayoutHelper layoutHelper1 = new LinearLayoutHelper();
        layoutHelper1.setAspectRatio(2.0f);
        SubAdapter adapter = new SubAdapter(this, layoutHelper1, 3);
        List<DelegateAdapter.Adapter> adapters = new LinkedList<>();
        adapters.add(adapter);
        delegateAdapter.setAdapters(adapters);
    }


    static class SubAdapter extends DelegateAdapter.Adapter<MainViewHolder> {

        private Context mContext;

        private LayoutHelper mLayoutHelper;


        private VirtualLayoutManager.LayoutParams mLayoutParams;
        private int mCount = 0;


        public SubAdapter(Context context, LayoutHelper layoutHelper, int count) {
            this(context, layoutHelper, count, new VirtualLayoutManager.LayoutParams(ViewGroup
                    .LayoutParams
                    .MATCH_PARENT, 300));
        }

        public SubAdapter(Context context, LayoutHelper layoutHelper, int count, @NonNull
                VirtualLayoutManager.LayoutParams layoutParams) {
            this.mContext = context;
            this.mLayoutHelper = layoutHelper;
            this.mCount = count;
            this.mLayoutParams = layoutParams;
        }

        @Override
        public LayoutHelper onCreateLayoutHelper() {
            return mLayoutHelper;
        }

        @Override
        public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MainViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item,
                    parent, false));
        }

        @Override
        public void onBindViewHolder(MainViewHolder holder, int position) {
            // only vertical
            holder.itemView.setLayoutParams(
                    new VirtualLayoutManager.LayoutParams(mLayoutParams));
        }


        @Override
        protected void onBindViewHolderWithOffset(MainViewHolder holder, int position, int
                offsetTotal) {
            ((TextView) holder.itemView.findViewById(R.id.title)).setText(Integer.toString
                    (offsetTotal));
        }

        @Override
        public int getItemCount() {
            return mCount;
        }
    }

    static class MainViewHolder extends RecyclerView.ViewHolder {

        public static volatile int existing = 0;
        public static int createdTimes = 0;

        public MainViewHolder(View itemView) {
            super(itemView);
            createdTimes++;
            existing++;
        }

        @Override
        protected void finalize() throws Throwable {
            existing--;
            super.finalize();
        }
    }
}
