package com.example.hp.chuangyiapp.utils;

import android.util.Log;

public class LoginUtil {
    public static boolean isLogin(){
        return PreferenceUtil.getBoolean(PreferenceUtil.IS_LOGIN);
    }
    public static void login(String token,String name){
        PreferenceUtil.saveBoolean(PreferenceUtil.IS_LOGIN,true);
        PreferenceUtil.saveString(PreferenceUtil.TOKEN,token);
        Log.i("LOGIN",token);
        PreferenceUtil.saveString(PreferenceUtil.USER_NAME,name);
    }
    public static void quitLogin(){
        PreferenceUtil.saveBoolean(PreferenceUtil.IS_LOGIN,false);
    }
    public static String getToken(){
        return PreferenceUtil.getString(PreferenceUtil.TOKEN);
    }
}
