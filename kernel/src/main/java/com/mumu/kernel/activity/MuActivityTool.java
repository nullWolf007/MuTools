package com.mumu.kernel.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by nullWolf on 2019/11/1.
 * <p>
 * 封装activity相关工具类
 */
public class MuActivityTool {
    /**
     * 存放activity的列表
     */
    private static HashMap<Class<?>, Activity> activities = new LinkedHashMap<>();

    /**
     * hashmap添加Activity
     *
     * @param activity
     */
    public static void addActivity(@NonNull Activity activity, @NonNull Class<?> clz) {
        activities.put(clz, activity);
    }

    /**
     * hashmap移除activity
     *
     * @param activity
     */
    public static void removeActivity(@NonNull Activity activity) {
        if (activities.containsValue(activity)) {
            activities.remove(activity.getClass());
        }
    }

    /**
     * hashmap移除activity,并且finish这个页面
     *
     * @param activity Actvity对象
     */
    public static void finshActivity(@NonNull Activity activity) {
        if (activities.containsValue(activity)) {
            activities.get(activity.getClass()).finish();
            activities.remove(activity.getClass());
        }
    }

    /**
     * hashmap移除activity,并且finish这个页面
     *
     * @param clazz class对象
     */
    public static void finshActivity(@NonNull Class clazz) {
        if (activities.containsKey(clazz)) {
            activities.get(clazz).finish();
            activities.remove(clazz);
        }
    }

    /**
     * 获得指定activity实例
     *
     * @param clazz Activity 的类对象
     * @return activity
     */
    public static <T extends Activity> T getActivity(@NonNull Class<T> clazz) {
        return (T) activities.get(clazz);
    }

    /**
     * 判断一个Activity 是否存在
     *
     * @param clz class对象
     * @return true表示存在 false表示不存在
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static <T extends Activity> boolean isActivityExist(@NonNull Class<T> clz) {
        boolean res;
        Activity activity = getActivity(clz);
        if (activity == null) {
            res = false;
        } else {
            if (activity.isFinishing() || activity.isDestroyed()) {
                res = false;
            } else {
                res = true;
            }
        }
        return res;
    }


    /**
     * 移除所有的Activity
     * finish的同时也清空hashmap
     */
    public static void finishAllActivity() {
        if (activities != null && activities.size() > 0) {
            Set<Map.Entry<Class<?>, Activity>> sets = activities.entrySet();
            for (Map.Entry<Class<?>, Activity> s : sets) {
                if (!s.getValue().isFinishing()) {
                    s.getValue().finish();
                }
            }
        }
        activities.clear();
    }

}
