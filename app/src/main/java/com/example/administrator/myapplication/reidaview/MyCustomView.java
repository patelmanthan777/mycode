//package com.example.administrator.myapplication.reidaview;
//
//import android.content.Context;
//import android.graphics.Canvas;
//import android.graphics.Path;
//import android.util.AttributeSet;
//import android.view.View;
//
///**
// * Created by Administrator on 2016/12/15.
// */
//
//public class MyCustomView extends View {
//    private int dataCount = 6;
//
//    public MyCustomView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        initView();
//    }
//
//    public MyCustomView(Context context) {
//        this(context, null);
//    }
//
//    public void initView() {
//
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        drawPolygon(canvas);
//
//    }
//
//    /**
//     * 绘制多边形
//     *
//     * @param canvas 画布
//     */
//    private void drawPolygon(Canvas canvas) {
//        Path path = new Path();
//        for (int i = 0; i < dataCount; i++) {
//            if (i == 0) {
//                path.moveTo(getPoint(i).x, getPoint(i).y);
//            } else {
//                path.lineTo(getPoint(i).x, getPoint(i).y);
//            }
//        }
//
//        //闭合路径
//        path.close();
//        canvas.drawPath(path, mainPaint);
//    }
//
//}
