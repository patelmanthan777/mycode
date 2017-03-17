package com.example.administrator.myapplication.recyclerview.xrecycleview;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;


/**
 * @author 作者  : luohl
 * @version 创建时间：2016/8/1.
 *          类说明:
 */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int space;
    private int spanCount;
    private int headCount;

    public SpaceItemDecoration() {
    }

    public SpaceItemDecoration(int space, int spanCount, int headCount) {
        this.space = (int) (space + 0.5f);
        this.headCount = headCount;
        this.spanCount = spanCount;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State
            state) {
//        super.getItemOffsets(outRect, view, parent, state);
        XRecyclerView xRecyclerView = (XRecyclerView) parent;
        outRect.left = space;
        outRect.bottom = space;
        int position = parent.getChildLayoutPosition(view);
        if ((position - headCount) % spanCount == 0 || xRecyclerView.isHeader(position)) {
            outRect.left = 0;
        }
        if (xRecyclerView.isHeader(position) && (position != headCount - 1)) {
            outRect.bottom = 0;
        }
    }

    public int getHeadCount() {
        return headCount;
    }

    public void setHeadCount(int headCount) {
        this.headCount = headCount;
    }
}
