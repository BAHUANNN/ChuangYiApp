package com.example.hp.chuangyiapp;

import android.app.Application;
import android.provider.Settings;

import com.example.base.Global;
import com.example.myaspect.MuxiMonitor;



public class App extends Application {
    private static App instance;
    public static final String LOGIN_SUCCESS = "loginSuccess";
    public static final String LOGIN_ID = "id";

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Global. setApplication(this);
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
