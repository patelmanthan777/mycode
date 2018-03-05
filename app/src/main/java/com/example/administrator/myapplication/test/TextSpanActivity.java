package com.example.administrator.myapplication.test;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.widget.TextView;

import com.example.administrator.myapplication.R;

/**
 * Created by xcy on 2017/12/20 0020.
 */

public class TextSpanActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_span);

        TextView textView = new TextView(this);
        textView.setTextSize(15);
        setContentView(textView);
        SpannableString showString = new SpannableString("bsheiryqddufpeodstt345" +
                "3333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333336");
        MyIm imageSpan = new MyIm(this, R.mipmap.big_yushou);
        MyIm imageSpan2 = new MyIm(this, R.mipmap.ic_launcher);
        MyIm imageSpan21 = new MyIm(this, R.mipmap.ic_launcher);
        showString.setSpan(imageSpan, 0, 1, ImageSpan.ALIGN_BASELINE);
        showString.setSpan(imageSpan2, 27, 30,ImageSpan.ALIGN_BASELINE);
        showString.setSpan(imageSpan21, 77, 78, ImageSpan.ALIGN_BASELINE);
        textView.setText(showString);
    }

    public class MyIm extends ImageSpan {
        public MyIm(Context arg0, int arg1) {
            super(arg0, arg1);
        }

//        public int getSize(Paint paint, CharSequence text, int start, int end,
//                           Paint.FontMetricsInt fm) {
//            Drawable d = getDrawable();
//            Rect rect = d.getBounds();
//            if (fm != null) {
//                Paint.FontMetricsInt fmPaint = paint.getFontMetricsInt();
//                int fontHeight = fmPaint.bottom - fmPaint.top;
//                int drHeight = rect.bottom - rect.top;
//
//                int top = drHeight / 2 - fontHeight / 4;
//                int bottom = drHeight / 2 + fontHeight / 4;
//
//                fm.ascent = -bottom;
//                fm.top = -bottom;
//                fm.bottom = top;
//                fm.descent = top;
//            }
//            return rect.right;
//        }

        @Override
        public void draw(Canvas canvas, CharSequence text, int start, int end,
                         float x, int top, int y, int bottom, Paint paint) {
            // image to draw
            Drawable b = getDrawable();
            // font metrics of text to be replaced
            Paint.FontMetricsInt fm = paint.getFontMetricsInt();
            int transY = (y + fm.descent + y + fm.ascent) / 2
                    - b.getBounds().bottom / 2;

            canvas.save();
            canvas.translate(x, transY);
            b.draw(canvas);
            canvas.restore();
        }
    }
}
