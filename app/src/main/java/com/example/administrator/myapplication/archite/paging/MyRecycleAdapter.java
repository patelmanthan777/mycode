package com.example.administrator.myapplication.archite.paging;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.archite.paging.db.ListItemBean;

/**
 * Created by xcy on 2018/3/5 0005.
 */

public class MyRecycleAdapter extends PagedListAdapter<ListItemBean, MyRecycleAdapter
        .MyViewHolder> {

    protected MyRecycleAdapter(@NonNull DiffUtil.ItemCallback<ListItemBean> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout
                        .adapter_fragment_my_list,
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.initData(position, getItem(position));
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvTitle;
        private TextView mTvContent;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTvTitle = itemView.findViewById(R.id.tv_title);
            mTvContent = itemView.findViewById(R.id.tv_content);
        }

        public void initData(int position, ListItemBean bean) {
            mTvTitle.setText(position + ":" + bean.title);
            mTvContent.setText(bean.content + "|" + bean.mUserBean.username + "|" + bean
                    .mUserBean.age);
        }
    }

}
