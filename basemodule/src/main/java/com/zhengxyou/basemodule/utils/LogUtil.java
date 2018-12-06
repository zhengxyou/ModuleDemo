package com.zhengxyou.basemodule.utils;

import com.socks.library.KLog;

public class LogUtil {
    public static void v(Class c, Object msg) {
        KLog.v(c.getSimpleName(), msg);
    }

    public static void v(Class c, Object... objects) {
        KLog.v(c.getSimpleName(), objects);
    }

    public static void v(Object msg) {
        KLog.v(msg);
    }

    public static void d(Class c, Object msg) {
        KLog.d(c.getSimpleName(), msg);
    }

    public static void d(Class c, Object... objects) {
        KLog.d(c.getSimpleName(), objects);
    }

    public static void d(Object msg) {
        KLog.d(msg);
    }

    public static void i(Class c, Object msg) {
        KLog.i(c.getSimpleName(), msg);
    }

    public static void i(Class c, Object... objects) {
        KLog.i(c.getSimpleName(), objects);
    }

    public static void i(Object msg) {
        KLog.i(msg);
    }

    public static void w(Class c, Object msg) {
        KLog.w(c.getSimpleName(), msg);
    }

    public static void w(Class c, Object... objects) {
        KLog.w(c.getSimpleName(), objects);
    }

    public static void w(Object msg) {
        KLog.w(msg);
    }

    public static void e(Class c, Object msg) {
        KLog.e(c.getSimpleName(), msg);
    }

    public static void e(Class c, Object... objects) {
        KLog.e(c.getSimpleName(), objects);
    }

    public static void e(Object msg) {
        KLog.e(msg);
    }

    public static void a(Class c, Object msg) {
        KLog.a(c.getSimpleName(), msg);
    }

    public static void a(Class c, Object... objects) {
        KLog.a(c.getSimpleName(), objects);
    }

    public static void a(Object msg) {
        KLog.a(msg);
    }

    public static void json(Class c, String json) {
        KLog.json(c.getSimpleName(), json);
    }

    public static void json(String json) {
        KLog.json(json);
    }

    public static void xml(Class c, String msg) {
        KLog.xml(c.getSimpleName(), msg);
    }

    public static void xml(String msg) {
        KLog.xml(msg);
    }
}
