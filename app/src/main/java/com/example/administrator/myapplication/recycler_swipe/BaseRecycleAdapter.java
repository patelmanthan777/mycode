package com.example.administrator.myapplication.recycler_swipe;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by xcy on 2016/7/26.
 * recycleview适配器基类
 * T: 实体类
 * H:  viewholder
 */

public abstract class BaseRecycleAdapter<T, H extends BaseRecycleViewHolder<T>> extends RecyclerView
        .Adapter<H> implements OnItemMoveListener {
    Context mContext;
    protected List<T> mList;
    protected int layoutId;
    int mSelectPosition;

    public BaseRecycleAdapter(Context context, int layoutId) {
        mContext = context;
        mList = new ArrayList<>();
        this.layoutId = layoutId;
    }

    @Override
    public H onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(layoutId, parent, false);
        return onCreateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(H holder, int position) {
        holder.initData(position);
        holder.initData(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * 子类实现此方法，返回viewholder对象
     */
    public abstract H onCreateViewHolder(View view);

    public void setData(List<T> data) {
        if (data == null) {
            clearData();
            return;
        }
        if (data.size() == 0) {
            clearData();
            return;
        }
        mList = data;
        notifyDataSetChanged();
    }

    public void addData(List<T> data) {
        int oldSize = mList.size();
        mList.addAll(data);
        for (int i = oldSize; i < mList.size(); i++) {
            notifyItemChanged(i);
        }
    }

    public void clearData() {
        mList.clear();
        notifyDataSetChanged();
    }

    /**
     * 替换更新item
     */
    public void replaceItem(T t) {
        mList.set(mSelectPosition, t);
        notifyDataSetChanged();
    }

    /**
     * 删除item
     */
    public void deleteItem(int position) {
        mList.remove(mSelectPosition);
//        notifyDataSetChanged();
        notifyItemRemoved(position);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mList, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return false;
    }

    @Override
    public void onItemSwipe(int position) {
        deleteItem(position);
    }
}
