package com.example.hp.chuangyiapp.utils;

public class LoginUtil {
    public static boolean isLogin(){
        return PreferenceUtil.getBoolean(PreferenceUtil.IS_LOGIN);
    }
    public static void login(){
        PreferenceUtil.saveBoolean(PreferenceUtil.IS_LOGIN,true);
    }
    public static void quitLogin(){
        PreferenceUtil.saveBoolean(PreferenceUtil.IS_LOGIN,false);
    }
}
