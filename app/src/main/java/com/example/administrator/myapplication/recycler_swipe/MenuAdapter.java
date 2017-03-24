/*
 * Copyright 2016 Yan Zhenjie
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.administrator.myapplication.recycler_swipe;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.recycler_swipe.swpie.SwipeMenuAdapter;

/**
 * Created by YOLANDA on 2016/7/22.
 */
public class MenuAdapter extends SwipeMenuAdapter<String, MenuAdapter.DefaultViewHolder> {

    public MenuAdapter(Context context) {
        super(context, R.layout.item_menu);
    }

    @Override
    public DefaultViewHolder onCreateViewHolder(View view) {
        return new DefaultViewHolder(view);
    }

    public static class DefaultViewHolder extends BaseRecycleViewHolder<String> implements View
            .OnClickListener {
        TextView tvTitle;

        public DefaultViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        }

        @Override
        public void initData(String s) {
            this.tvTitle.setText(s);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), getAdapterPosition() + "", Toast.LENGTH_SHORT).show();
        }
    }

}
