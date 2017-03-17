package com.example.administrator.myapplication.recyclerview.xrecycleview;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XRecyclerView extends RecyclerView {
    private Context mContext;
    private boolean isLoadingData = false;
    private boolean isNoMore = false;
    private int mRefreshProgressStyle = ProgressStyle.BallSpinFadeLoader;
    //    private int mLoadingMoreProgressStyle = ProgressStyle.BallRotate;
    private int mLoadingMoreProgressStyle = ProgressStyle.BallSpinFadeLoader;
    //    private ArrayList<View> mHeaderViews = new ArrayList<>();

    private Map<Integer, View> mHeadViewMap = new HashMap<>();
    private List<Integer> mViewTypeMap = new ArrayList<>();

    private Map<Integer, View> mFooterViewMap = new HashMap<>();
    private List<Integer> mFootTypeMap = new ArrayList<>();

    //    private ArrayList<View> mFootViews = new ArrayList<>();
    private Adapter mWrapAdapter;
    private float mLastY = -1;
    private static final float DRAG_RATE = 2;
    private LoadingListener mLoadingListener;
    private ArrowRefreshHeader mRefreshHeader;
    private LoadingMoreFooter mLoadingMoreFooter;
    private boolean pullRefreshEnabled = true;
    private boolean loadingMoreEnabled = true;
    private static final int TYPE_NORMAL = 0;
    private static final int TYPE_FOOTER = -3;
    private static final int TYPE_BLANKFOOTER = -4;
    private static final int HEADER_INIT_INDEX = 10000;
    private static final int FOOTER_INIT_INDEX = 100000;
    //    private List<Integer> sHeaderTypes = new ArrayList<>();
    private int mPageCount = 0;
    //adapter没有数据的时候显示,类似于listView的emptyView
    private View mEmptyView;
    private TextView mFootTv;

    private boolean scroll = true;

    public XRecyclerView(Context context) {
        this(context, null);
    }

    public XRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        init();
    }

    private void init() {
        if (pullRefreshEnabled) {
            ArrowRefreshHeader refreshHeader = new ArrowRefreshHeader(getContext());
            addHeaderView(refreshHeader);
            mRefreshHeader = refreshHeader;
            mRefreshHeader.setProgressStyle(mRefreshProgressStyle);
        }
    }

    public void addHeaderView(View... views) {
        for (View v : views) {
            if (mHeadViewMap.containsValue(v)) {
                continue;
            }
            int key = HEADER_INIT_INDEX + mHeadViewMap.size();
            mViewTypeMap.add(key);
            mHeadViewMap.put(key, v);
        }
    }

//    /**
//     * 注意，这个position要看是否有下拉刷新的头部，有头部,位置是要加1的
//     */
//    public void addHeaderView(View view, int position) {
//        if (mHeadViewMap.containsValue(view)) {
//            return;
//        }
//        int size = mHeadViewMap.size();
//        if (position >= size) {
//            addHeaderView(view);
//            return;
//        }
//        for (int i = size - 1; i >= position; i--) {
//            //如果position在总数以内，则需要将position以下的view都往后移动一下,且要从最后一个开始移动
//            int k = HEADER_INIT_INDEX + i;
//            mViewTypeMap.add(k + 1);
//            mHeadViewMap.put(k + 1, mHeadViewMap.get(k));
//        }
//        mViewTypeMap.set(position, position + HEADER_INIT_INDEX);
//        mHeadViewMap.put(position + HEADER_INIT_INDEX, view);
//
//    }

    public boolean containHead(View view) {
        return mHeadViewMap.containsValue(view);
    }

    public void removeHeaderView(View view) {
        if (view == null) {
            return;
        }
        if (!mHeadViewMap.containsValue(view)) {
            return;
        }
        removeView(view);
        for (Map.Entry entry : mHeadViewMap.entrySet()) {
            if (entry.getValue().equals(view)) {
                if (mViewTypeMap.indexOf(entry.getKey()) != -1) {
                    mViewTypeMap.remove(entry.getKey());
                }
                mHeadViewMap.remove(entry.getKey());
                break;
            }
        }
    }

//    public void removeHeaderView(int position) {
//        if (position > mViewTypeMap.size()) {
//            return;
//        }
//        removeViewAt(position);
//        int size = mViewTypeMap.size();
//        for (int i = position; i < size - 1; i++) {
//            int k = HEADER_INIT_INDEX + i;
//            mHeadViewMap.put(k, mHeadViewMap.get(k + 1));
//        }
//        mHeadViewMap.remove(HEADER_INIT_INDEX + size - 1);
//        mViewTypeMap.remove(size - 1);
//    }

    public void addFooterView(View... views) {
        for (View v : views) {
            int key = FOOTER_INIT_INDEX + mFooterViewMap.size();
            mFootTypeMap.add(key);
            mFooterViewMap.put(key, v);
        }
    }

    private int mFooterHeight = LayoutParams.WRAP_CONTENT;

    public void setBlankFooter(int height) {
        if (mLoadingMoreFooter == null) {
            mFooterHeight = height;
            return;
        }
        ViewGroup.LayoutParams params = mLoadingMoreFooter.getLayoutParams();
        params.height = height;
        mLoadingMoreFooter.setLayoutParams(params);
        setFooteTextNull();
    }

    public void loadMoreComplete() {
        if (mLoadingMoreFooter == null) {
            return;
        }
        isLoadingData = false;
        mLoadingMoreFooter.setState(LoadingMoreFooter.STATE_COMPLETE);
    }

    public void setNoMore(boolean noMore) {
        if (mLoadingMoreFooter == null) {
            return;
        }
        this.isNoMore = noMore;
        mLoadingMoreFooter.setState(this.isNoMore ? LoadingMoreFooter.STATE_NOMORE :
                LoadingMoreFooter.STATE_COMPLETE);
    }

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

    public void reset() {
        setNoMore(false);
        loadMoreComplete();
        refreshComplete();
    }

    public void noMoreLoading() {
        if (mLoadingMoreFooter == null) {
            return;
        }
        isLoadingData = false;
        isNoMore = true;
        mLoadingMoreFooter.setState(LoadingMoreFooter.STATE_NOMORE);
    }

    public void refreshComplete() {
        if (mRefreshHeader != null) {
            mRefreshHeader.refreshComplete();
        }
    }

    public void setRefreshHeader(ArrowRefreshHeader refreshHeader) {
        mRefreshHeader = refreshHeader;
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

    public void setEmptyView(View emptyView) {
        this.mEmptyView = emptyView;
    }

    public View getEmptyView() {
        return mEmptyView;
    }

    @Override
    public void setAdapter(Adapter adapter) {
        setItemAnimator(new MyItemDimenon());
        mWrapAdapter = new WrapAdapter(adapter);
        super.setAdapter(mWrapAdapter);
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
    public boolean onTouchEvent(MotionEvent ev) {
        if (!scroll) {
            return false;
        }

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
        return !(mHeadViewMap == null || mHeadViewMap.isEmpty()) && mHeadViewMap.get
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

        private int headerPosition = 1;

        public WrapAdapter(Adapter adapter) {
            this.adapter = adapter;
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

        public boolean isContentHeader(int position) {
            return position >= 1 && position < mHeadViewMap.size();
        }

        public boolean isRefreshHeader(int position) {
            return position == 0;
        }


        private int mCurrentPosition;

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (mHeadViewMap.containsKey(viewType)) {
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

    public void setNoScroll() {
        scroll = false;
    }

}