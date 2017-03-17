package com.example.administrator.myapplication.behavior;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.administrator.myapplication.R;

import java.lang.ref.WeakReference;


/**
 * Created by xcy on 2017/3/8 0008.
 */

public class TestBehavior extends AppBarLayout.ScrollingViewBehavior {
    private WeakReference<View> mReferenceView;
    private Context mContext;

    public TestBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    /**
     * 与指定的view确定依赖关系
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        if (dependency instanceof AppBarLayout) {
            mReferenceView = new WeakReference<>(dependency.findViewById(R.id.toolbar));
            return true;
        }
        return false;
    }

    /**
     * @param parent
     * @param child      子view即自己在布局文件定义了behavior的view
     * @param dependency 与子view互相依赖的view 即前面layoutDependsOn方法设置了的view
     * @return
     */
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        return super.onDependentViewChanged(parent, child, dependency);
    }

    /**
     * @param nestedScrollAxes 滑动的方向
     *                         用户按下手指开始滑动的判断，返回true，自己处理滑动
     */
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View
            directTargetChild, View target, int nestedScrollAxes) {
        return child instanceof RecyclerView && nestedScrollAxes == ViewCompat
                .SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedScrollAccepted(CoordinatorLayout coordinatorLayout, View child, View
            directTargetChild, View target, int nestedScrollAxes) {
    }

    /**
     * 即将滑动，其中consumed数组，可以修改这个数组来表示你到底处理掉了多少像素。假设用户滑动了 100px，你做了 90px 的位移，那么就需要把 consumed[1] 改成
     * 90（下标 0、1 分别对应 x、y 轴），这样 NSC 就能知道，然后继续处理剩下的 10px
     * consumed[0]=dx
     * consumed[1]=dy
     */
    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target,
                                  int dx, int dy, int[] consumed) {
        Log.e("asdasd", "onNestedPreScroll" + dx + "|" + dy + "|" + consumed[0] + "|" +
                consumed[1]);
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int
            dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        Log.e("asdasd", "onNestedScroll" + dxConsumed + "|" + dyConsumed + "|" + dxUnconsumed +
                "|" + dyUnconsumed);
        if (dyConsumed > 0) {
            getReferenceView().setSearch();
        } else {
            getReferenceView().setTitle();
        }
    }

    /**
     * 手指松开之后发生的惯性滑动
     */
    @Override
    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, View child, View target,
                                    float velocityX, float velocityY) {
        return super.onNestedPreFling(coordinatorLayout, child, target, velocityX, velocityY);
    }

    @Override
    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, View child, View target,
                                 float velocityX, float velocityY, boolean consumed) {
        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY,
                consumed);
    }

    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target) {
        super.onStopNestedScroll(coordinatorLayout, child, target);
    }

    public Titlebar getReferenceView() {
        return (Titlebar) mReferenceView.get();
    }
}
