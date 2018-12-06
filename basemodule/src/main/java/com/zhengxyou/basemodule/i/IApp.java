package com.zhengxyou.basemodule.i;

public interface IApp {
    /**
     * 初始化一些数据,如果需要的话
     */
    void initData();

    /**
     * 初始化第三方依赖
     */
    void initLibs();

    /**
     * 初始化阿里路由框架
     */
    void initARouter();

    /**
     * 对组件进行一些初始化,如果需要的话
     */
    void initModule();

    /**
     * 一些可以不需要马上初始化的第三方，可以放在IntentService进行初始化，提高应用的启动速度
     */
    void initLibsByIntentService();

    /**
     * 初始化第三方日志工具
     */
    void initLog();
}
