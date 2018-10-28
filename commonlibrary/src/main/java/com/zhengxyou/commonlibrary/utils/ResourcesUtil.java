package com.zhengxyou.commonlibrary.utils;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;

public class ResourcesUtil {
    /**
     * 获取图片名称获取图片的资源id的方法
     */
    public static int getResourceByImageName(String imageName, Context context) {
        return context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
    }


    /**
     * 产生shape类型的drawable
     */
    public static GradientDrawable getBackgroundDrawable(int solidColor, int strokeColor, int strokeWidth, float radius) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(solidColor);
        drawable.setStroke(strokeWidth, strokeColor);
        drawable.setCornerRadius(radius);
        return drawable;
    }
}
