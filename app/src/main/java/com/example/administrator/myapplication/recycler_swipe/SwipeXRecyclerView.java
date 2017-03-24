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
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.example.administrator.myapplication.recycler_swipe.swpie.OnSwipeMenuItemClickListener;
import com.example.administrator.myapplication.recycler_swipe.swpie.SwipeMenuAdapter;
import com.example.administrator.myapplication.recycler_swipe.swpie.SwipeMenuCreator;
import com.example.administrator.myapplication.recycler_swipe.swpie.SwipeMenuLayout;
import com.example.administrator.myapplication.recyclerview.xrecycleview.ArrowRefreshHeader;
import com.example.administrator.myapplication.recyclerview.xrecycleview.LoadingMoreFooter;
import com.example.administrator.myapplication.recyclerview.xrecycleview.MyItemDimenon;
import com.example.administrator.myapplication.recyclerview.xrecycleview.ProgressStyle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yan Zhenjie on 2016/7/27.
 */
public class SwipeXRecyclerView extends RecyclerView {
    private boolean allowSwipeDelete = false;
    private boolean isLoadingData = false;
    private boolean isNoMore = false;
    private int mRefreshProgressStyle = ProgressStyle.BallSpinFadeLoader;
    private int mLoadingMoreProgressStyle = ProgressStyle.BallSpinFadeLoader;
    public static final int LEFT_DIRECTION = 1;
    public static final int RIGHT_DIRECTION = -1;
    private static final float DRAG_RATE = 2;
    private static final int TYPE_NORMAL = 0;
    private static final int TYPE_FOOTER = -3;
    private static final int HEADER_INIT_INDEX = 10000;
    protected int mOldTouchedPosition = INVALID_POSITION;

    private SparseArray<View> mHeadViewMap = new SparseArray<>();
    private List<Integer> mViewTypeMap = new ArrayList<>();

    private WrapAdapter mWrapAdapter;
    private float mLastY = -1;
    private int mDownX;
    private int mDownY;
    private LoadingListener mLoadingListener;
    private ArrowRefreshHeader mRefreshHeader;
    private LoadingMoreFooter mLoadingMoreFooter;
    private boolean pullRefreshEnabled = true;
    private boolean loadingMoreEnabled = true;

    protected int mScaleTouchSlop;
    protected SwipeMenuLayout mOldSwipedLayout;


    /**
     * Invalid position.
     */
    private static final int INVALID_POSITION = -1;

    public SwipeXRecyclerView(Context context) {
        this(context, null);
    }

    public SwipeXRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwipeXRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mScaleTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        if (pullRefreshEnabled) {
            ArrowRefreshHeader refreshHeader = new ArrowRefreshHeader(getContext());
            addHeaderView(refreshHeader);
            mRefreshHeader = refreshHeader;
            mRefreshHeader.setProgressStyle(mRefreshProgressStyle);
        }
    }

    public void addHeaderView(View... views) {
        for (View v : views) {
            if (mHeadViewMap.indexOfValue(v) != -1) {
                continue;
            }
            int key = HEADER_INIT_INDEX + mHeadViewMap.size();
            mViewTypeMap.add(key);
            mHeadViewMap.put(key, v);
        }
    }

    public boolean containHead(View view) {
        return mHeadViewMap.indexOfValue(view) != -1;
    }

    public void removeHeaderView(View view) {
        if (view == null) {
            return;
        }
        if (mHeadViewMap.indexOfValue(view) == -1) {
            return;
        }
        removeView(view);
        int key = mHeadViewMap.indexOfValue(view);
        if (mViewTypeMap.indexOf(key) != -1) {
            mViewTypeMap.remove(key);
        }
        mHeadViewMap.remove(key);
    }

    /**
     * 设置是否已经没有数据了
     * false:还有数据
     * true：无数据
     */
    public void setNoMore(boolean noMore) {
        if (mLoadingMoreFooter == null) {
            return;
        }
        this.isNoMore = noMore;
        mLoadingMoreFooter.setState(this.isNoMore ? LoadingMoreFooter.STATE_NOMORE :
                LoadingMoreFooter.STATE_COMPLETE);
    }

    /**
     * 设置是否显示加载更多
     */
    public void setLoadingMoreEnabled(boolean enabled) {
        loadingMoreEnabled = enabled;
        if (mLoadingMoreFooter == null) {
            return;
        }
        mLoadingMoreFooter.setVisibility(enabled ? VISIBLE : GONE);
    }

    /**
     * 将底部的text的值清空
     */
    public void setFooteTextNull() {
        if (mLoadingMoreFooter == null) {
            return;
        }
        isNoMore = true;
        mLoadingMoreFooter.setState(LoadingMoreFooter.STATE_NOMORE);
        mLoadingMoreFooter.setText("");
    }

    /**
     * 没有更多数据
     */
    public void noMoreLoading() {
        if (mLoadingMoreFooter == null) {
            return;
        }
        isLoadingData = false;
        isNoMore = true;
        mLoadingMoreFooter.setState(LoadingMoreFooter.STATE_NOMORE);
    }

    public void reset() {
        setNoMore(false);
        loadMoreComplete();
        refreshComplete();
    }

    public void refreshComplete() {
        if (mRefreshHeader != null) {
            mRefreshHeader.refreshComplete();
        }
    }

    public void loadMoreComplete() {
        if (mLoadingMoreFooter == null) {
            return;
        }
        isLoadingData = false;
        mLoadingMoreFooter.setState(LoadingMoreFooter.STATE_COMPLETE);
    }

    public void setPullRefreshEnabled(boolean enabled) {
        pullRefreshEnabled = enabled;
    }


    public void setRefreshProgressStyle(int style) {
        mRefreshProgressStyle = style;
        if (mRefreshHeader != null) {
            mRefreshHeader.setProgressStyle(style);
        }
    }

    public void setLoadingMoreProgressStyle(int style) {
        mLoadingMoreProgressStyle = style;
        if (mLoadingMoreFooter != null) {
            mLoadingMoreFooter.setProgressStyle(style);
        }
    }

    public void setArrowImageView(int resId) {
        if (mRefreshHeader != null) {
            mRefreshHeader.setArrowImageView(resId);
        }
    }

    @Override
    public void setAdapter(Adapter adapter) {
        setItemAnimator(new MyItemDimenon());
        mWrapAdapter = new WrapAdapter(adapter);
        super.setAdapter(mWrapAdapter);
    }

    /**
     * Set to create menu listener.
     *
     * @param swipeMenuCreator listener.
     */
    public void setSwipeMenuCreator(SwipeMenuCreator swipeMenuCreator) {
        Adapter adapter = mWrapAdapter.getAdapter();
        if (adapter instanceof SwipeMenuAdapter) {
            ((SwipeMenuAdapter) adapter).setSwipeMenuCreator(swipeMenuCreator);
        }
    }

    /**
     * Set to click menu listener.
     */
    public void setSwipeMenuItemClickListener(OnSwipeMenuItemClickListener listener) {
        Adapter adapter = mWrapAdapter.getAdapter();
        if (adapter instanceof SwipeMenuAdapter) {
            ((SwipeMenuAdapter) adapter).setSwipeMenuItemClickListener(listener);
        }
    }

    /**
     * open menu on left.
     *
     * @param position position.
     */
    public void smoothOpenLeftMenu(int position) {
        smoothOpenLeftMenu(position, SwipeMenuLayout.DEFAULT_SCROLLER_DURATION);
    }

    /**
     * open menu on left.
     *
     * @param position position.
     * @param duration time millis.
     */
    public void smoothOpenLeftMenu(int position, int duration) {
        smoothOpenMenu(position, LEFT_DIRECTION, duration);
    }

    /**
     * open menu on right.
     *
     * @param position position.
     */
    public void smoothOpenRightMenu(int position) {
        smoothOpenRightMenu(position, SwipeMenuLayout.DEFAULT_SCROLLER_DURATION);
    }

    /**
     * open menu on right.
     *
     * @param position position.
     * @param duration time millis.
     */
    public void smoothOpenRightMenu(int position, int duration) {
        smoothOpenMenu(position, RIGHT_DIRECTION, duration);
    }

    /**
     * open menu.
     *
     * @param position  position.
     * @param direction use { LEFT_DIRECTION}, { RIGHT_DIRECTION}.
     * @param duration  time millis.
     */
    public void smoothOpenMenu(int position, int direction, int duration) {
        if (mOldSwipedLayout != null) {
            if (mOldSwipedLayout.isMenuOpen()) {
                mOldSwipedLayout.smoothCloseMenu();
            }
        }
        ViewHolder vh = findViewHolderForAdapterPosition(position);
        if (vh != null) {
            View itemView = getSwipeMenuView(vh.itemView);
            if (itemView instanceof SwipeMenuLayout) {
                mOldSwipedLayout = (SwipeMenuLayout) itemView;
                if (direction == RIGHT_DIRECTION) {
                    mOldTouchedPosition = position;
                    mOldSwipedLayout.smoothOpenRightMenu(duration);
                } else if (direction == LEFT_DIRECTION) {
                    mOldTouchedPosition = position;
                    mOldSwipedLayout.smoothOpenLeftMenu(duration);
                }
            }
        }
    }

    /**
     * Close menu.
     */
    public void smoothCloseMenu() {
        if (mOldSwipedLayout != null && mOldSwipedLayout.isMenuOpen()) {
            mOldSwipedLayout.smoothCloseMenu();
        }
    }

    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);
        if (state == RecyclerView.SCROLL_STATE_IDLE && mLoadingListener != null && !isLoadingData
                && loadingMoreEnabled) {
            LayoutManager layoutManager = getLayoutManager();
            int lastVisibleItemPosition;
            if (layoutManager instanceof GridLayoutManager) {
                lastVisibleItemPosition = ((GridLayoutManager) layoutManager)
                        .findLastVisibleItemPosition();
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                int[] into = new int[((StaggeredGridLayoutManager) layoutManager).getSpanCount()];
                ((StaggeredGridLayoutManager) layoutManager).findLastVisibleItemPositions(into);
                lastVisibleItemPosition = findMax(into);
            } else {
                lastVisibleItemPosition = ((LinearLayoutManager) layoutManager)
                        .findLastVisibleItemPosition();
            }
            if (layoutManager.getChildCount() > 0
                    && lastVisibleItemPosition >= layoutManager.getItemCount() - 1 &&
                    layoutManager.getItemCount() > layoutManager.getChildCount() && !isNoMore &&
                    mRefreshHeader.getState() < ArrowRefreshHeader.STATE_REFRESHING) {
                isLoadingData = true;
                mLoadingMoreFooter.setState(LoadingMoreFooter.STATE_LOADING);
                mLoadingListener.onLoadMore();
            }
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        boolean isIntercepted = super.onInterceptTouchEvent(e);
        if (allowSwipeDelete)  // swipe and menu conflict.
            return isIntercepted;
        else {
            if (e.getPointerCount() > 1) return true;
            int action = e.getAction();
            int x = (int) e.getX();
            int y = (int) e.getY();
            switch (action) {
                case MotionEvent.ACTION_DOWN: {
                    mDownX = x;
                    mDownY = y;
                    isIntercepted = false;
                    //触摸之后判断之前d触摸有无打开menu，有打开，则关闭，并拦截当前触摸事件
                    int touchingPosition = getChildAdapterPosition(findChildViewUnder(x, y));
                    if (touchingPosition != mOldTouchedPosition && mOldSwipedLayout != null &&
                            mOldSwipedLayout.isMenuOpen()) {
                        mOldSwipedLayout.smoothCloseMenu();
                        isIntercepted = true;
                    }
                    if (isIntercepted) {
                        mOldSwipedLayout = null;
                        mOldTouchedPosition = INVALID_POSITION;
                    } else {
                        //记录下当前触摸的位置以及view
                        ViewHolder vh = findViewHolderForAdapterPosition(touchingPosition);
                        if (vh != null) {
                            View itemView = getSwipeMenuView(vh.itemView);
                            if (itemView instanceof SwipeMenuLayout) {
                                mOldSwipedLayout = (SwipeMenuLayout) itemView;
                                mOldTouchedPosition = touchingPosition;
                            }
                        }
                    }
                    break;
                }
                // They are sensitive to retain sliding and inertia.
                case MotionEvent.ACTION_MOVE: {
                    isIntercepted = handleUnDown(x, y, isIntercepted);
                    if (mOldSwipedLayout == null) break;
                    ViewParent viewParent = getParent();
                    if (viewParent == null) break;

                    int disX = mDownX - x;
                    // 向左滑，显示右侧菜单，或者关闭左侧菜单。
                    boolean showRightCloseLeft = disX > 0 && (mOldSwipedLayout.hasRightMenu() ||
                            mOldSwipedLayout
                                    .isLeftCompleteOpen());
                    // 向右滑，显示左侧菜单，或者关闭右侧菜单。
                    boolean showLeftCloseRight = disX < 0 && (mOldSwipedLayout.hasLeftMenu() ||
                            mOldSwipedLayout
                                    .isRightCompleteOpen());
                    viewParent.requestDisallowInterceptTouchEvent(showRightCloseLeft ||
                            showLeftCloseRight);
                }
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL: {
                    isIntercepted = handleUnDown(x, y, isIntercepted);
                    break;
                }
            }


        }
        return isIntercepted;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mLastY == -1) {
            mLastY = ev.getRawY();
        }
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastY = ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float deltaY = ev.getRawY() - mLastY;
                mLastY = ev.getRawY();
                if (isOnTop() && pullRefreshEnabled) {
                    mRefreshHeader.onMove(deltaY / DRAG_RATE);
                    if (mRefreshHeader.getVisibleHeight() > 0 && mRefreshHeader.getState() <
                            ArrowRefreshHeader.STATE_REFRESHING) {
                        return false;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                mLastY = -1; // reset
                if (isOnTop() && pullRefreshEnabled) {
                    if (mRefreshHeader.releaseAction()) {
                        if (mLoadingListener != null) {
                            mLoadingListener.onRefresh();
                        }
                    }
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    private boolean handleUnDown(int x, int y, boolean defaultValue) {
        int disX = mDownX - x;
        int disY = mDownY - y;

        // swipe
        if (Math.abs(disX) > mScaleTouchSlop && Math.abs(disX) > Math.abs(disY))
            return false;
        // click
        if (Math.abs(disY) < mScaleTouchSlop && Math.abs(disX) < mScaleTouchSlop)
            return false;
        return defaultValue;
    }

    private View getSwipeMenuView(View itemView) {
        if (itemView instanceof SwipeMenuLayout) return itemView;
        List<View> unvisited = new ArrayList<>();
        unvisited.add(itemView);
        while (!unvisited.isEmpty()) {
            View child = unvisited.remove(0);
            if (!(child instanceof ViewGroup)) { // view
                continue;
            }
            if (child instanceof SwipeMenuLayout) return child;
            ViewGroup group = (ViewGroup) child;
            final int childCount = group.getChildCount();
            for (int i = 0; i < childCount; i++) unvisited.add(group.getChildAt(i));
        }
        return itemView;
    }

    public void enableSwipeDelete(boolean enable) {
        allowSwipeDelete = enable;
    }

    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    private int findMin(int[] firstPositions) {
        int min = firstPositions[0];
        for (int value : firstPositions) {
            if (value < min) {
                min = value;
            }
        }
        return min;
    }

    private boolean isOnTop() {
        return !(mHeadViewMap == null || mHeadViewMap.size() == 0) && mHeadViewMap.get
                (HEADER_INIT_INDEX).getParent() != null;
    }

    public boolean isHeader(int position) {
        return position >= 0 && position < mHeadViewMap.size();
    }

    public boolean isFooter(int position) {
        return position == mWrapAdapter.getItemCount() - 1;
    }

    public int getHeadersCount() {
        return mHeadViewMap.size();
    }

    private class WrapAdapter extends Adapter<ViewHolder> {

        private Adapter adapter;

        public WrapAdapter(Adapter adapter) {
            this.adapter = adapter;
        }

        public Adapter getAdapter() {
            return adapter;
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
            LayoutManager manager = recyclerView.getLayoutManager();
            if (manager instanceof GridLayoutManager) {
                final GridLayoutManager gridManager = ((GridLayoutManager) manager);
                gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        return (isHeader(position) || isFooter(position))
                                ? gridManager.getSpanCount() : 1;
                    }
                });
            }
        }

        @Override
        public void onViewAttachedToWindow(ViewHolder holder) {
            super.onViewAttachedToWindow(holder);
            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
            if (lp != null
                    && lp instanceof StaggeredGridLayoutManager.LayoutParams
                    && (isHeader(holder.getLayoutPosition()) || isFooter(holder.getLayoutPosition
                    ()))) {
                StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager
                        .LayoutParams) lp;
                p.setFullSpan(true);
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (mHeadViewMap.indexOfKey(viewType) != -1) {
                return new SimpleViewHolder(mHeadViewMap.get(viewType));
            }
            if (viewType == TYPE_FOOTER) {
                mLoadingMoreFooter = new LoadingMoreFooter(getContext());
                mLoadingMoreFooter.setVisibility(View.GONE);
                mLoadingMoreFooter.setProgressStyle(mLoadingMoreProgressStyle);
                return new SimpleViewHolder(mLoadingMoreFooter);
            }
            return adapter.onCreateViewHolder(parent, viewType);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            if (isHeader(position)) {
                return;
            }
            if (isFooter(position)) {
                return;
            }

            int adjPosition = position - getHeadersCount();

            int adapterCount;
            if (adapter != null) {
                adapterCount = adapter.getItemCount();
                if (adjPosition < adapterCount) {
                    adapter.onBindViewHolder(holder, adjPosition);
                }
            }
        }

        @Override
        public int getItemCount() {
            if (adapter != null) {
                return getHeadersCount() + 1 + adapter.getItemCount();
            } else {
                return getHeadersCount() + 1;
            }
        }

        @Override
        public int getItemViewType(int position) {
            if (isHeader(position)) {
                return mViewTypeMap.get(position);
            }
            if (isFooter(position)) {
                return TYPE_FOOTER;
            }
            int adjPosition = position - getHeadersCount();
            int adapterCount;
            if (adapter != null) {
                adapterCount = adapter.getItemCount();
                if (adjPosition < adapterCount) {
                    return adapter.getItemViewType(adjPosition);
                }
            }
            return TYPE_NORMAL;
        }

        @Override
        public long getItemId(int position) {
            if (adapter != null && position >= getHeadersCount()) {
                int adjPosition = position - getHeadersCount();
                int adapterCount = adapter.getItemCount();
                if (adjPosition < adapterCount) {
                    return adapter.getItemId(adjPosition);
                }
            }
            return -1;
        }

        @Override
        public void unregisterAdapterDataObserver(AdapterDataObserver observer) {
            if (adapter != null) {
                adapter.unregisterAdapterDataObserver(observer);
            }
        }

        @Override
        public void registerAdapterDataObserver(AdapterDataObserver observer) {
            if (adapter != null) {
                adapter.registerAdapterDataObserver(observer);
            }
        }

        private class SimpleViewHolder extends ViewHolder {
            public SimpleViewHolder(View itemView) {
                super(itemView);
            }
        }

    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
    }

    public void setLoadingListener(LoadingListener listener) {
        mLoadingListener = listener;
    }

    public interface LoadingListener {

        void onRefresh();

        void onLoadMore();
    }

    public void setRefreshing(boolean refreshing) {
        if (refreshing && pullRefreshEnabled && mLoadingListener != null && mRefreshHeader
                .getState() != ArrowRefreshHeader.STATE_REFRESHING) {
            mRefreshHeader.setState(ArrowRefreshHeader.STATE_REFRESHING);
            mRefreshHeader.onMove(mRefreshHeader.mMeasuredHeight);
            scrollToPosition(0);
            mLoadingListener.onRefresh();
        }
    }
}