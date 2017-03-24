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
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.Method;

/**
 * Created by Yan Zhenjie on 2016/7/25.
 */
public class ResCompat {

    public static Drawable getDrawable(Context context, int drawableId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            return context.getDrawable(drawableId);
        else
            return context.getResources().getDrawable(drawableId);
    }

    public static int getColor(Context context, int colorId) {
        return getColor(context, colorId, null);
    }

    public static int getColor(Context context, int colorId, Theme theme) {
        Resources resources = context.getResources();
        Class<?> resourcesClass = resources.getClass();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            try {
                Method getColorMethod = resourcesClass.getMethod("getColor", int.class, Theme
                        .class);
                getColorMethod.setAccessible(true);
                return (Integer) getColorMethod.invoke(resources, colorId, theme);
            } catch (Throwable e) {
            }
        else
            try {
                Method getColorMethod = resourcesClass.getMethod("getColor", int.class);
                getColorMethod.setAccessible(true);
                return (Integer) getColorMethod.invoke(resources, colorId);
            } catch (Throwable e) {
            }
        return Color.BLACK;
    }

    public static void setBackground(View view, Drawable background) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
            view.setBackground(background);
        else
            view.setBackgroundDrawable(background);
    }

    public static void setTextAppearance(TextView view, int textAppearance) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            view.setTextAppearance(textAppearance);
        else
            view.setTextAppearance(view.getContext(), textAppearance);
    }
}
