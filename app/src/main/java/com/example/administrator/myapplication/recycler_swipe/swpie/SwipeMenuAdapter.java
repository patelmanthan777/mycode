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
package com.example.administrator.myapplication.recycler_swipe.swpie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.recycler_swipe.BaseRecycleAdapter;
import com.example.administrator.myapplication.recycler_swipe.BaseRecycleViewHolder;
import com.example.administrator.myapplication.recycler_swipe.SwipeXRecyclerView;

/**
 * Created by Yan Zhenjie on 2016/7/27.
 */
public abstract class SwipeMenuAdapter<T, VH extends BaseRecycleViewHolder<T>> extends
        BaseRecycleAdapter<T, VH> {

    /**
     * Swipe menu creator。
     */
    private SwipeMenuCreator mSwipeMenuCreator;

    /**
     * Swipe menu click listener。
     */
    private OnSwipeMenuItemClickListener mSwipeMenuItemClickListener;

    public SwipeMenuAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    /**
     * Set to create menu listener.
     *
     * @param swipeMenuCreator listener.
     */
    public void setSwipeMenuCreator(SwipeMenuCreator swipeMenuCreator) {
        this.mSwipeMenuCreator = swipeMenuCreator;
    }

    /**
     * Set to click menu listener.
     *
     * @param swipeMenuItemClickListener listener.
     */
    public void setSwipeMenuItemClickListener(OnSwipeMenuItemClickListener
                                                      swipeMenuItemClickListener) {
        this.mSwipeMenuItemClickListener = swipeMenuItemClickListener;
    }

    @Override
    public final VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View contentView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent,
                false);
        if (mSwipeMenuCreator != null) {
            SwipeMenuLayout swipeMenuLayout = (SwipeMenuLayout) LayoutInflater.from(parent
                    .getContext()).inflate(R.layout.swipe_layout, parent, false);

            SwipeMenu swipeLeftMenu = new SwipeMenu(swipeMenuLayout, viewType);
            SwipeMenu swipeRightMenu = new SwipeMenu(swipeMenuLayout, viewType);

            mSwipeMenuCreator.onCreateMenu(swipeLeftMenu, swipeRightMenu, viewType);

            int leftMenuCount = swipeLeftMenu.getMenuItems().size();
            if (leftMenuCount > 0) {
                SwipeMenuView swipeLeftMenuView = (SwipeMenuView) swipeMenuLayout.findViewById(R
                        .id.swipe_left);
                // noinspection WrongConstant
                swipeLeftMenuView.setOrientation(swipeLeftMenu.getOrientation());
                swipeLeftMenuView.bindMenu(swipeLeftMenu, SwipeXRecyclerView.LEFT_DIRECTION);
                swipeLeftMenuView.bindMenuItemClickListener(mSwipeMenuItemClickListener,
                        swipeMenuLayout);
            }

            int rightMenuCount = swipeRightMenu.getMenuItems().size();
            if (rightMenuCount > 0) {
                SwipeMenuView swipeRightMenuView = (SwipeMenuView) swipeMenuLayout.findViewById(R
                        .id.swipe_right);
                // noinspection WrongConstant
                swipeRightMenuView.setOrientation(swipeRightMenu.getOrientation());
                swipeRightMenuView.bindMenu(swipeRightMenu, SwipeXRecyclerView.RIGHT_DIRECTION);
                swipeRightMenuView.bindMenuItemClickListener(mSwipeMenuItemClickListener,
                        swipeMenuLayout);
            }

            if (leftMenuCount > 0 || rightMenuCount > 0) {
                ViewGroup viewGroup = (ViewGroup) swipeMenuLayout.findViewById(R.id.swipe_content);
                viewGroup.addView(contentView);
                contentView = swipeMenuLayout;
            }
        }
        return onCreateViewHolder(contentView);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        View itemView = holder.itemView;
        //这里需要绑定每一个侧滑的view到当前的viewholder，为了点击的时候知道目前的position
        if (itemView instanceof SwipeMenuLayout) {
            SwipeMenuLayout swipeMenuLayout = (SwipeMenuLayout) itemView;
            int childCount = swipeMenuLayout.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childView = swipeMenuLayout.getChildAt(i);
                if (childView instanceof SwipeMenuView) {
                    ((SwipeMenuView) childView).bindAdapterViewHolder(holder);
                }
            }
        }
        super.onBindViewHolder(holder, position);
    }
}