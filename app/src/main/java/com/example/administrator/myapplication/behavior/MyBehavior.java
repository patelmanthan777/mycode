package com.example.administrator.myapplication.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by xcy on 2017/3/8 0008.
 */

public class MyBehavior extends CoordinatorLayout.Behavior {

    public MyBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 与指定的view确定依赖关系
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
//        Log.e("asdasd", String.valueOf(dependency.getId() == R.id.first));
        return true;
    }

    /**
     * @param parent
     * @param child      子view即自己在布局文件定义了behavior的view
     * @param dependency 与子view互相依赖的view 即前面layoutDependsOn方法设置了的view
     * @return
     */
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        child.setY(dependency.getY() + dependency.getHeight());
        return true;
    }

}
