package com.example.base;

import android.app.Application;

public class Global {
    public static Application sApp;

    /**
     * 获取上层 application 对象
     * @return
     */
    public static Application getApplication(){
        return sApp;
    }

    /**
     * 上层 application 在 oncreate 时调用此方法
     * @param app
     */
    public static void setApplication(Application app){
        sApp = app;
    }
}
