package com.example.hp.chuangyiapp;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.myaspect.MuxiMonitor;

import org.aspectj.lang.annotation.Around;


public class App extends Application {
    private static App instance;
    public static final String LOGIN_SUCCESS = "loginSuccess";
    public static final String LOGIN_ID = "id";

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        MuxiMonitor.init(getDeviceId(), "url");
    }

    public static App getInstance() {
        return instance;
    }

    private String getDeviceId() {
        String androidID = Settings.Secure.getString(getInstance().getContentResolver(), Settings.Secure.ANDROID_ID);
        return androidID;
    }
}
