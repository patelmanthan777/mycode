package com.example.administrator.myapplication.recycler_swipe;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

/**
 * Created by xcy on 2016/7/25.
 */
public abstract class BaseRecycleViewHolder<T> extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;
    private View mConvertView;
    private Context mContext;

    public BaseRecycleViewHolder(Context context, View itemView) {
        super(itemView);
        mContext = context;
        mConvertView = itemView;
        mViews = new SparseArray<>();
    }

    public BaseRecycleViewHolder(View itemView) {
        super(itemView);
        mConvertView = itemView;
        mViews = new SparseArray<>();
    }

    /**
     * 通过viewId获取控件
     */
    private <V extends View> V getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (V) view;
    }

    public void setText(int id, String text) {
        TextView tv = getView(id);
        tv.setText(text);
    }

    public void setSelected(int id, boolean isSelected) {
        View view = getView(id);
        view.setSelected(isSelected);
    }

    public void setClickable(int id, boolean isClick) {
        View view = getView(id);
        view.setClickable(isClick);
    }

    public void setVisibility(int id, int visibility) {
        View view = getView(id);
        view.setVisibility(visibility);
    }

    public void setConvertView(View view) {
        mConvertView = view;
    }

    public View getConvertView() {
        return mConvertView;
    }

    public abstract void initData(T t);

    public void initData(int position) {
    }

}
