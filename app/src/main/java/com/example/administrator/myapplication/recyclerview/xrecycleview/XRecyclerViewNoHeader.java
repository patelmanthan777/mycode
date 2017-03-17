package com.example.administrator.myapplication.recyclerview.xrecycleview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class XRecyclerViewNoHeader extends RecyclerView {

    private boolean isLoadingData = false;
    private boolean isNoMore = false;
    private int mLoadingMoreProgressStyle = ProgressStyle.BallSpinFadeLoader;
    private ArrayList<View> mFootViews = new ArrayList<>();
    private WrapAdapter mWrapAdapter;
    private LoadingListener mLoadingListener;
    private boolean loadingMoreEnabled = true;
    private static final int TYPE_NORMAL = 0;
    private static final int TYPE_FOOTER = -3;

    public XRecyclerViewNoHeader(Context context) {
        this(context, null);
    }

    public XRecyclerViewNoHeader(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XRecyclerViewNoHeader(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        LoadingMoreFooter footView = new LoadingMoreFooter(getContext());
        footView.setProgressStyle(mLoadingMoreProgressStyle);
        addFootView(footView);
        mFootViews.get(0).setVisibility(GONE);
    }


    public void addFootView(final View view) {
        mFootViews.clear();
        mFootViews.add(view);
    }

    public void loadMoreComplete() {
        Log.e("errorcuowu", "loadMoreComplete");
        isLoadingData = false;
        View footView = mFootViews.get(0);
        if (footView instanceof LoadingMoreFooter) {
            ((LoadingMoreFooter) footView).setState(LoadingMoreFooter.STATE_COMPLETE);
        } else {
            footView.setVisibility(GONE);
        }
    }

    public void setNoMore(boolean noMore) {
        this.isNoMore = noMore;
        View footView = mFootViews.get(0);
        ((LoadingMoreFooter) footView).setState(this.isNoMore ? LoadingMoreFooter.STATE_NOMORE :
                LoadingMoreFooter.STATE_COMPLETE);
    }

    public void reset() {
        setNoMore(false);
        loadMoreComplete();
    }

    public void noMoreLoading() {
        Log.e("errorcuowu", "noMoreLoading");
        isLoadingData = false;
        View footView = mFootViews.get(0);
        isNoMore = true;
        if (footView instanceof LoadingMoreFooter) {
            ((LoadingMoreFooter) footView).setState(LoadingMoreFooter.STATE_NOMORE);
        } else {
            footView.setVisibility(GONE);
        }
    }


    @Override
    public void setAdapter(Adapter adapter) {
        if (mWrapAdapter == null) {
            mWrapAdapter = new WrapAdapter(adapter);
        } else {
            mWrapAdapter.setAdapter(adapter);
        }
        super.setAdapter(mWrapAdapter);
    }


    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);
        Log.e("onScrollStateChanged", state + "");
        if (state == RecyclerView.SCROLL_STATE_IDLE && mLoadingListener != null && !isLoadingData
                && loadingMoreEnabled) {
            LayoutManager layoutManager = getLayoutManager();
            int lastVisibleItemPosition;
            lastVisibleItemPosition = ((LinearLayoutManager) layoutManager)
                    .findLastVisibleItemPosition();
            if (layoutManager.getChildCount() > 0
                    && lastVisibleItemPosition >= layoutManager.getItemCount() - 1 &&
                    layoutManager.getItemCount() > layoutManager.getChildCount() && !isNoMore) {
                View footView = mFootViews.get(0);
                isLoadingData = true;
                if (footView instanceof LoadingMoreFooter) {
                    ((LoadingMoreFooter) footView).setState(LoadingMoreFooter.STATE_LOADING);
                } else {
                    footView.setVisibility(View.VISIBLE);
                }
                mLoadingListener.onLoadMore();
            }
        }
    }

    public boolean isFooter(int position) {
        return position < mWrapAdapter.getItemCount() && position >= mWrapAdapter.getItemCount()
                - mFootViews.size();
    }

    private class WrapAdapter extends Adapter<ViewHolder> {

        private Adapter adapter;

        WrapAdapter(Adapter adapter) {
            this.adapter = adapter;
        }

        public void setAdapter(Adapter adapter) {
            this.adapter = adapter;
        }

        public int getFootersCount() {
            return mFootViews.size();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == TYPE_FOOTER) {
                return new SimpleViewHolder(mFootViews.get(0));
            }
            return adapter.onCreateViewHolder(parent, viewType);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            int adapterCount;
            if (adapter != null) {
                adapterCount = adapter.getItemCount();
                if (position < adapterCount) {
                    adapter.onBindViewHolder(holder, position);
                }
            }
        }

        @Override
        public int getItemCount() {
            if (adapter != null) {
                return getFootersCount() + adapter.getItemCount();
            } else {
                return getFootersCount();
            }
        }

        @Override
        public int getItemViewType(int position) {
            if (isFooter(position)) {
                return TYPE_FOOTER;
            }
            int adapterCount;
            if (adapter != null) {
                adapterCount = adapter.getItemCount();
                if (position < adapterCount) {
                    return adapter.getItemViewType(position);
                }
            }
            return TYPE_NORMAL;
        }

        @Override
        public long getItemId(int position) {
            if (adapter != null) {
                int adapterCount = adapter.getItemCount();
                if (position < adapterCount) {
                    return adapter.getItemId(position);
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

    public void setLoadingListener(LoadingListener listener) {
        mLoadingListener = listener;
    }

    public interface LoadingListener {

        void onRefresh();

        void onLoadMore();
    }

}