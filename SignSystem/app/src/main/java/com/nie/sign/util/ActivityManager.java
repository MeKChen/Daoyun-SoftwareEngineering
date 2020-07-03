package com.nie.sign.util;


import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.text.TextUtils;

import java.util.List;
import java.util.Stack;

/**
 * Created by xsymin on 2019/4/25.
 * 统一应用程序中所有的Activity的栈管理（单例）
 * 涉及到activity的添加、删除指定、删除当前、删除所有、返回栈大小的方法
 */

public class ActivityManager {

    public static boolean isForeground(Context context, String className) {
        if (context == null || TextUtils.isEmpty(className)) {
            return false;
        }

        android.app.ActivityManager am = (android.app.ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<android.app.ActivityManager.RunningTaskInfo> list = am.getRunningTasks(10);
        if (list != null && list.size() > 0) {
            ComponentName cpn = list.get(0).topActivity;
            if (cpn.getClassName().contains(className)) {
                return true;
            }
        }

        return false;

    }


    //单例模式：饿汉式
    private ActivityManager() {

    }

    private static ActivityManager activityManager = new ActivityManager();

    public static ActivityManager getInstance() {
        return activityManager;
    }

    //提供栈的对象
    private Stack<Activity> activityStack = new Stack<>();

    public void add(Activity activity) {
        if (activity != null) {
            activityStack.add(activity);
        }
    }


    //删除指定的activity
    public void remove(Activity activity) {
        if (activity != null) {
            for (int i = activityStack.size() - 1; i >= 0; i--) {
                Activity currentActivity = activityStack.get(i);
                if (currentActivity.getClass().equals(activity.getClass())) {
                    currentActivity.finish();//销毁当前的activity
                    activityStack.remove(i);//从栈空间移除
                }
            }
        }
    }

    //删除当前的activity
    public void removeCurrent() {
        //方式一：
//        Activity activity = activityStack.get(activityStack.size() - 1);
//        activity.finish();
//        activityStack.remove(activityStack.size() - 1);

        //方式二：
        Activity activity = activityStack.lastElement();
        activity.finish();
        activityStack.remove(activity);
    }

    //删除所有的activity
    public void removeAll() {
        for (int i = activityStack.size() - 1; i >= 0; i--) {
            Activity activity = activityStack.get(i);
            activity.finish();
            activityStack.remove(activity);
        }
    }

    //返回栈大小
    public int size() {
        return activityStack.size();
    }
}

