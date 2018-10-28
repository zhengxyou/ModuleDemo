package com.zhengxyou.commonlibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import java.util.Set;

/**
 * SharePreferences工具类
 */
public class SharedPreferencesUtil {
    private SharedPreferences manager;

    /**
     * @param context 上下文，建议Application的
     * @param name    文件名
     */
    public SharedPreferencesUtil(Context context, String name) {
        manager = context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    public void clear() {
        this.manager.edit().clear().apply();
    }

    public void clearKey(String key) {
        this.manager.edit().remove(key).apply();
    }

    public boolean getBoolean(String key, boolean value) {
        return this.manager.getBoolean(key, value);
    }

    public boolean putBoolean(String key, boolean value) {
        return this.manager.edit().putBoolean(key, value).commit();
    }

    public void putBooleanByApply(String key, boolean value) {
        this.manager.edit().putBoolean(key, value).apply();
    }

    public int getInt(String key, int value) {
        return this.manager.getInt(key, value);
    }

    public boolean putInt(String key, int value) {
        return this.manager.edit().putInt(key, value).commit();
    }

    public void putIntByApply(String key, int value) {
        this.manager.edit().putInt(key, value).apply();
    }

    public boolean putString(String key, String value) {
        return this.manager.edit().putString(key, value).commit();
    }

    public void putStringByApply(String key, String value) {
        this.manager.edit().putString(key, value).apply();
    }

    public String getString(String key, String value) {
        return this.manager.getString(key, value);
    }

    public boolean putFloat(String key, float value) {
        return this.manager.edit().putFloat(key, value).commit();
    }

    public void putFloatByApply(String key, float value) {
        this.manager.edit().putFloat(key, value).apply();
    }

    public float getFloat(String key, float value) {
        return this.manager.getFloat(key, value);
    }

    public boolean putLong(String key, long value) {
        return this.manager.edit().putLong(key, value).commit();
    }

    public void putLongByApply(String key, long value) {
        this.manager.edit().putLong(key, value).apply();
    }

    public long getLong(String key, long value) {
        return this.manager.getLong(key, value);
    }

    public Set<String> getSet(String key) {
        return this.manager.getStringSet(key, null);
    }

    public boolean putSet(String key, Set<String> value) {
        return this.manager.edit().putStringSet(key, value).commit();
    }

    public void putSetByApply(String key, Set<String> value) {
        this.manager.edit().putStringSet(key, value).apply();
    }

    public boolean contains(@NonNull final String key) {
        return this.manager.contains(key);
    }
}
