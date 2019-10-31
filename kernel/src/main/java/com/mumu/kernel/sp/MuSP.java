package com.mumu.kernel.sp;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharedPreferences工具类
 * Created by nullWolf on 2019/10/31.
 */
public class MuSP {

    /**
     * 向指定的xml写入key键对应的String类型value值
     *
     * @param context
     * @param name    xml的名字 相同的会放入同一个xml
     * @param key     键
     * @param value   值
     */
    public static void putString(Context context, String name, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * 指定的xml读取key对应的String值
     *
     * @param name xml名字
     * @param key  键
     * @param defValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public static String getString(Context context, String name, String key, String defValue) {
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        String value;
        value = sp.getString(key, defValue);
        return value;
    }

    /**
     * 向指定的xml写入key键对应的int类型value值
     *
     * @param context
     * @param name    xml的名字 相同的会放入同一个xml
     * @param key     键
     * @param value   值
     */
    public static void putInt(Context context, String name, String key, int value) {
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    /**
     * 指定的xml读取key对应的int值
     *
     * @param name xml名字
     * @param key  键
     * @param defValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public static int getInt(Context context, String name, String key, int defValue) {
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        int value;
        value = sp.getInt(key, defValue);
        return value;
    }

    /**
     * 向指定的xml写入key键对应的long类型value值
     *
     * @param context
     * @param name    xml的名字 相同的会放入同一个xml
     * @param key     键
     * @param value   值
     */
    public static void putLong(Context context, String name, String key, long value) {
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    /**
     * 指定的xml读取key对应的long值
     *
     * @param name xml名字
     * @param key  键
     * @param defValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public static long getLong(Context context, String name, String key, long defValue) {
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        long value;
        value = sp.getLong(key, defValue);
        return value;
    }

    /**
     * 向指定的xml写入key键对应的float类型value值
     *
     * @param context
     * @param name    xml的名字 相同的会放入同一个xml
     * @param key     键
     * @param value   值
     */
    public static void putFloat(Context context, String name, String key, float value) {
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    /**
     * 指定的xml读取key对应的float值
     *
     * @param name xml名字
     * @param key  键
     * @param defValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public static float getFloat(Context context, String name, String key, float defValue) {
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        float value;
        value = sp.getFloat(key, defValue);
        return value;
    }

    /**
     * 向指定的xml写入key键对应的float类型boolean值
     *
     * @param context
     * @param name    xml的名字 相同的会放入同一个xml
     * @param key     键
     * @param value   值
     */
    public static void putBoolean(Context context, String name, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * 指定的xml读取key对应的boolean值
     *
     * @param name xml名字
     * @param key  键
     * @param defValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public static boolean getBoolean(Context context, String name, String key, boolean defValue) {
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        boolean value;
        value = sp.getBoolean(key, defValue);
        return value;
    }

    /**
     * 清除指定的xml信息
     *
     * @param context 上下文
     * @param name    xml名字
     */
    public static void clearPreferenceByName(Context context, String name) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    /**
     * 清除指定的xml的key的信息
     *
     * @param context 上下文
     * @param name    xml名字
     * @param key     键
     */
    public static void clearPreferenceByNameAndKey(Context context, String name, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (key != null) {
            editor.remove(key);
        }
        editor.apply();
    }
}
