package com.nie.sign.application;

import android.app.Application;
import android.content.Context;

import com.amap.api.location.AMapLocation;
import com.nie.sign.presenter.net.bean.UserInfoBean;


public class SystemApplication extends Application {
    public static Context context;
    public static String token = "";

    public static UserInfoBean userInfo;
    public static AMapLocation amapLocation;

    @Override
    public void onCreate() {
        context = this;
        super.onCreate();


    }

    public static Context getContext() {
        return context;
    }


}
