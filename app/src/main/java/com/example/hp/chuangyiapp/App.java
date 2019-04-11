package com.example.hp.chuangyiapp;

import android.app.Application;

public class App extends Application {
    private static App instance;
    public static final String LOGIN_SUCCESS = "loginSuccess";
    public static final String LOGIN_ID = "id";

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static App getInstance(){
        return instance;
    }
}
