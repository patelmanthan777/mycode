/*
 * Copyright (C) 2015 Paul Burke
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

import android.graphics.Canvas;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;


/**
 * recycler拖动或者滑动的处理
 * ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback();
 * ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(callback);
 * mItemTouchHelper.attachToRecyclerView(recyclerView);
 */
public class SimpleItemTouchHelperCallback extends ItemTouchHelper.Callback {

    public static final float ALPHA_FULL = 1.0f;

    //自定义可以控制移动以及拖拽用的，例如某个position的可以拖动，某些不行
    private OnItemMoveFlagsListener onItemMoveFlagsListener;
    //拖拽 移动结束之后调用
    private OnItemMoveListener onItemMoveListener;
    //item状态发生变化之后调用
    private OnItemStateChangedListener onItemStateChangedListener;


    public SimpleItemTouchHelperCallback() {
    }

    /**
     * 是否支持长按拖拽，false不支持
     * 也可以调用ItemTouchHelper.startDrag(RecyclerView.ViewHolder) 方法来开始一个拖动
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    /**
     * 是否支持滑动
     * 也可以主动调用ItemTouchHelper.startSwipe(RecyclerView.ViewHolder)
     */
    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    /**
     * 这个方法返回手势移动的方向，上下左右
     * 以下两个参数
     * dragFlags:拖拽 但是需要长按才会出来
     * swipeFlags:滑动
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        if (onItemMoveFlagsListener != null) {
            int dragFlags = onItemMoveFlagsListener.onDragFlags(recyclerView, viewHolder);
            int swipeFlags = onItemMoveFlagsListener.onSwipeFlags(recyclerView, viewHolder);
            return makeMovementFlags(dragFlags, swipeFlags);
        } else {
            //适用GridLayoutManager类型和LinearLayoutManager类型。
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof GridLayoutManager) {
                //拖拽的方向。
                int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT
                        | ItemTouchHelper.RIGHT;
                int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                //侧滑的方向：left right。
                return makeMovementFlags(dragFlags, swipeFlags);
            } else if (layoutManager instanceof LinearLayoutManager) {
                //线性方向分为：水平和垂直方向。
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
                if (linearLayoutManager.getOrientation() == LinearLayoutManager.HORIZONTAL) {
                    int dragFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                    int swipeFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                    return makeMovementFlags(dragFlags, swipeFlags);
                } else {
                    //拖拽的方向。
                    int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                    //侧滑的方向：left right。
                    int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                    return makeMovementFlags(dragFlags, swipeFlags);
                }
            }
        }
        return makeMovementFlags(0, 0);
    }

    /**
     * 移动对应的drag之后的回掉
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder source, RecyclerView
            .ViewHolder target) {
        //由于同一个recyclerview会有多个viewholder，这里先判断
        //拖拽后的目标viewholder与原始的是否一致，不一致直接返回
        if (source.getItemViewType() != target.getItemViewType()) {
            return false;
        }
        if (onItemMoveListener == null)
            return false;
        //回调刷新数据及界面。
        return onItemMoveListener.onItemMove(source.getAdapterPosition(), target
                .getAdapterPosition());
    }

    /**
     * 滑动后的回掉
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        //回调刷新数据及界面。
        if (onItemMoveListener != null)
            onItemMoveListener.onItemSwipe(viewHolder.getAdapterPosition());
    }

    /**
     * 拖拽或者滑动的时候，view开始重绘
     * 这里对滑动做了透明度的处理
     */
    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder
            viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        //判断当前是否是swipe方式：侧滑。
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            //1.ItemView--ViewHolder; 2.侧滑条目的透明度程度关联谁？dX(delta增量，范围：当前条目-width~width)。
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            float alpha = 1;
            if (layoutManager instanceof LinearLayoutManager) {
                int orientation = ((LinearLayoutManager) layoutManager).getOrientation();
                if (orientation == LinearLayoutManager.HORIZONTAL) {
                    alpha = 1 - Math.abs(dY) / viewHolder.itemView.getHeight();
                } else if (orientation == LinearLayoutManager.VERTICAL) {
                    alpha = 1 - Math.abs(dX) / viewHolder.itemView.getWidth();
                }
            }
            viewHolder.itemView.setAlpha(alpha);//1~0
        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }

    /**
     * 滑动或者拖拽开始之后会调用
     */
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        // We only want the active item to change
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            if (onItemStateChangedListener != null) {
                // Let the view holder know that this item is being moved or dragged
                onItemStateChangedListener.onSelectedChanged(viewHolder, actionState);
            }
        }
        super.onSelectedChanged(viewHolder, actionState);
    }

    /**
     * 一般在拖拽滑动等结束之后调用
     */
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        viewHolder.itemView.setAlpha(ALPHA_FULL);
        if (onItemStateChangedListener != null) {
            // Let the view holder know that this item is being moved or dragged
            onItemStateChangedListener.clearView();
        }
    }

    public void setOnItemMoveListener(OnItemMoveListener onItemMoveListener) {
        this.onItemMoveListener = onItemMoveListener;
    }

    public void setonItemMoveFlagsListener(OnItemMoveFlagsListener onItemMoveFlagsListener) {
        this.onItemMoveFlagsListener = onItemMoveFlagsListener;
    }

    public void setOnItemStateChangedListener(OnItemStateChangedListener
                                                      onItemStateChangedListener) {
        this.onItemStateChangedListener = onItemStateChangedListener;
    }
}
