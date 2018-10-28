package com.zhengxyou.commonlibrary.utils;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.view.Surface;
import android.view.Window;
import android.view.WindowManager;

public class ScreenUtils {
    /**
     * 以像素为单位返回屏幕宽度。
     *
     * @return the width of screen, in pixel
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        Point point = new Point();
        //noinspection ConstantConditions
        wm.getDefaultDisplay().getRealSize(point);
        return point.x;
    }

    /**
     * 以像素为单位返回屏幕高度。
     *
     * @return the height of screen, in pixel
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        Point point = new Point();
        //noinspection ConstantConditions
        wm.getDefaultDisplay().getRealSize(point);
        return point.y;
    }

    /**
     * 返回屏幕密度。
     *
     * @return the density of screen
     */
    public static float getScreenDensity() {
        return Resources.getSystem().getDisplayMetrics().density;
    }

    /**
     * 返回以每英寸点数表示的屏幕密度。
     *
     * @return the screen density expressed as dots-per-inch
     */
    public static int getScreenDensityDpi() {
        return Resources.getSystem().getDisplayMetrics().densityDpi;
    }

    /**
     * 全屏设置。
     *
     * @param activity The activity.
     */
    public static void setFullScreen(@NonNull Activity activity) {
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    /**
     * 设置非全屏
     *
     * @param activity The activity.
     */
    public static void setNonFullScreen(@NonNull Activity activity) {
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    /**
     * 切换全屏
     *
     * @param activity The activity.
     */
    public static void toggleFullScreen(@NonNull Activity activity) {
        int fullScreenFlag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        Window window = activity.getWindow();
        if ((window.getAttributes().flags & fullScreenFlag) == fullScreenFlag) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                    | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                    | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }

    /**
     * 返回是否全屏。
     *
     * @param activity The activity.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isFullScreen(@NonNull Activity activity) {
        int fullScreenFlag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        return (activity.getWindow().getAttributes().flags & fullScreenFlag) == fullScreenFlag;
    }

    /**
     * 将屏幕设置为横向。
     *
     * @param activity The activity.
     */
    public static void setLandscape(@NonNull Activity activity) {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    /**
     * 将屏幕设置为纵向。
     *
     * @param activity The activity.
     */
    public static void setPortrait(@NonNull final Activity activity) {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    /**
     * 返回屏幕是否为横向。
     *
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isLandscape(Context context) {
        return context.getApplicationContext().getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;
    }

    /**
     * 返回屏幕是否为纵向。
     *
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isPortrait(Context context) {
        return context.getApplicationContext().getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_PORTRAIT;
    }

    /**
     * 返回屏幕旋转的角度。
     *
     * @param activity The activity.
     * @return the rotation of screen
     */
    public static int getScreenRotation(@NonNull Activity activity) {
        switch (activity.getWindowManager().getDefaultDisplay().getRotation()) {
            case Surface.ROTATION_0:
                return 0;
            case Surface.ROTATION_90:
                return 90;
            case Surface.ROTATION_180:
                return 180;
            case Surface.ROTATION_270:
                return 270;
            default:
                return 0;
        }
    }

    /**
     * 返回是否锁定屏幕。
     *
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isScreenLock(Context context) {
        KeyguardManager km =
                (KeyguardManager) context.getApplicationContext().getSystemService(Context.KEYGUARD_SERVICE);
        //noinspection ConstantConditions
        return km.isKeyguardLocked();
    }


    /**
     * 返回睡眠时间
     *
     * @return the duration of sleep.
     */
    public static int getSleepDuration(Context context) {
        try {
            return Settings.System.getInt(
                    context.getApplicationContext().getContentResolver(),
                    Settings.System.SCREEN_OFF_TIMEOUT
            );
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
            return -123;
        }
    }

    /**
     * 返回设备是否为平板电脑。
     *
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isTablet(Context context) {
        return (context.getApplicationContext().getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
}
