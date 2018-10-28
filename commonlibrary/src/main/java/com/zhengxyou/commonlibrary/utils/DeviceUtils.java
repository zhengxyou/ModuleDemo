package com.zhengxyou.commonlibrary.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.annotation.RequiresPermission;

import static android.Manifest.permission.ACCESS_WIFI_STATE;
import static android.Manifest.permission.INTERNET;

public class DeviceUtils {

    /**
     * 得到设备mac地址
     * <p>必须申请
     * {@code <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />},
     * {@code <uses-permission android:name="android.permission.INTERNET" />}</p>
     *
     * @return the MAC address
     */
    @SuppressLint("HardwareIds")
    @RequiresPermission(allOf = {ACCESS_WIFI_STATE, INTERNET})
    public static String MAC(Context context) {
        String mac = "";
        WifiManager manager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (manager != null) {
            WifiInfo info = manager.getConnectionInfo();
            mac = info.getMacAddress();
        }
        return mac;
    }

    /**
     * 获取手机品牌
     */
    public static String getPhoneBrand() {
        return android.os.Build.BRAND;
    }

    /**
     * 获取手机型号
     */
    public static String getPhoneModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取手机Android版本
     */
    public static String getBuildVersion() {
        return android.os.Build.VERSION.RELEASE;
    }
}
