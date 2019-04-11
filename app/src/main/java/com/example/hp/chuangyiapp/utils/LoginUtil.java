package com.example.hp.chuangyiapp.utils;

public class LoginUtil {
    public static boolean isLogin(){
        return PreferenceUtil.getBoolean(PreferenceUtil.IS_LOGIN);
    }
}
