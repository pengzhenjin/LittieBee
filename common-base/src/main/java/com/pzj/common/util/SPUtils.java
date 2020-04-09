package com.pzj.common.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @ClassName: SPUtils
 * @Description: SharedPreferences 工具类
 * @Author: PengZhenjin
 * @CreateDate: 2019/9/21 15:53
 */
public class SPUtils {

    private SPUtils() {
        throw new UnsupportedOperationException("You can't instantiate me...");
    }

    public static void putString(Context context, String spName, String key, String value) {
        SharedPreferences settings = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getString(Context context, String spName, String key) {
        return getString(context, spName, key, null);
    }

    public static String getString(Context context, String spName, String key, String defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        return settings.getString(key, defaultValue);
    }

    public static void putInt(Context context, String spName, String key, int value) {
        SharedPreferences settings = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static int getInt(Context context, String spName, String key) {
        return getInt(context, spName, key, -1);
    }

    public static int getInt(Context context, String spName, String key, int defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        return settings.getInt(key, defaultValue);
    }

    public static void putLong(Context context, String spName, String key, long value) {
        SharedPreferences settings = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public static long getLong(Context context, String spName, String key) {
        return getLong(context, spName, key, -1);
    }

    public static long getLong(Context context, String spName, String key, long defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        return settings.getLong(key, defaultValue);
    }

    public static void putFloat(Context context, String spName, String key, float value) {
        SharedPreferences settings = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    public static float getFloat(Context context, String spName, String key) {
        return getFloat(context, spName, key, -1);
    }

    public static float getFloat(Context context, String spName, String key, float defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        return settings.getFloat(key, defaultValue);
    }

    public static void putBoolean(Context context, String spName, String key, boolean value) {
        SharedPreferences settings = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static boolean getBoolean(Context context, String spName, String key) {
        return getBoolean(context, spName, key, false);
    }

    public static boolean getBoolean(Context context, String spName, String key, boolean defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        return settings.getBoolean(key, defaultValue);
    }
}
