package com.pzj.common.util;

/**
 * @ClassName: LogUtils
 * @Description: 日志工具类
 * @Author: PengZhenjin
 * @CreateDate: 2019/9/21 14:24
 */
public class LogUtils {

    // 标签
    private static String tag = "pzj";

    // 是否开启日志显示
    private static boolean isOpenLog = true;

    private LogUtils() {
        throw new UnsupportedOperationException("You can't instantiate me...");
    }

    public static void setTag(String tag) {
        LogUtils.tag = tag;
    }

    public static void isOpenLog(boolean isOpenLog) {
        LogUtils.isOpenLog = isOpenLog;
    }

    public static void i(String msg) {
        if (isOpenLog) {
            android.util.Log.i(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (isOpenLog) {
            android.util.Log.i(tag, msg);
        }
    }

    public static void d(String msg) {
        if (isOpenLog) {
            android.util.Log.d(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (isOpenLog) {
            android.util.Log.d(tag, msg);
        }
    }

    public static void w(String msg) {
        if (isOpenLog) {
            android.util.Log.w(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (isOpenLog) {
            android.util.Log.w(tag, msg);
        }
    }

    public static void v(String msg) {
        if (isOpenLog) {
            android.util.Log.v(tag, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (isOpenLog) {
            android.util.Log.v(tag, msg);
        }
    }

    public static void e(String msg) {
        android.util.Log.e(tag, msg);
    }

    public static void e(String tag, String msg) {
        android.util.Log.e(tag, msg);
    }
}
