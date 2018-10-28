package com.zhengxyou.basemodule.base;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.zhengxyou.basemodule.BuildConfig;
import com.zhengxyou.basemodule.i.IApp;


public class BaseApplication extends Application implements IApp {
    @SuppressLint("StaticFieldLeak")
    static Context mGlobalContext;

    public static Context getContext() {
        return mGlobalContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mGlobalContext = this;
        initData();
        initLibs();
        initModule();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initLibs() {
        initARouter();
        initLibsByIntentService();
    }

    @Override
    public void initARouter() {
        // 这两行必须写在init之前，否则这些配置在init过程中将无效
        if (BuildConfig.DEBUG) {
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }

    @Override
    public void initModule() {

    }

    @Override
    public void initLibsByIntentService() {

    }
}
