package com.zhengxyou.basemodule.utils;


import com.zhengxyou.basemodule.base.BaseApplication;
import com.zhengxyou.commonlibrary.utils.SharedPreferencesUtil;

public class SharedPreferencesManager {
    private SharedPreferencesUtil mPreferencesUtil;
    private static volatile SharedPreferencesManager mPreferencesManager;

    private final String isFirstUse = "isFirstUse";

    private SharedPreferencesManager() {
        if (mPreferencesUtil == null) {
            mPreferencesUtil = new SharedPreferencesUtil(BaseApplication.getContext(), Constants.SharePreferences_Db_Name);
        }
    }

    public static SharedPreferencesManager getInstance() {
        if (null == mPreferencesManager) {
            synchronized (SharedPreferencesManager.class) {
                if (mPreferencesManager == null) {
                    mPreferencesManager = new SharedPreferencesManager();
                }
            }
        }
        return mPreferencesManager;
    }

    public void exitLogin() {

    }

    public boolean isLogin() {
        return false;
    }

    /**
     * 是否是第一次使用
     */
    public boolean isFirstUse() {
        boolean isFirstUse = mPreferencesUtil.getBoolean("isFirstUse", true);
        if (isFirstUse) {
            mPreferencesUtil.putBooleanByApply("isFirstUse", false);
        }
        return isFirstUse;
    }
}
