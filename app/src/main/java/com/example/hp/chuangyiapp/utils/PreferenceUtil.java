package com.example.hp.chuangyiapp.utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.hp.chuangyiapp.App;

public class PreferenceUtil {
    public static final String IS_LOGIN = "isLogin";
    public static final String USER_ID = "userId";
    public static final String USER_NAME = "userName";
    public static final String TOKEN = "token";

    public static void saveBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(App.getInstance()).edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static boolean getBoolean(String key) {
        return PreferenceManager.getDefaultSharedPreferences(App.getInstance()).getBoolean(key, false);
    }

    public static boolean getBoolean(String key, boolean def) {
        return PreferenceManager.getDefaultSharedPreferences(App.getInstance()).getBoolean(key, def);
    }


    public static void saveString(String key, String value) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(App.getInstance()).edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getString(String key) {
        return PreferenceManager.getDefaultSharedPreferences(App.getInstance()).getString(key, "");
    }

    public static String getString(String key, String def) {
        return PreferenceManager.getDefaultSharedPreferences(App.getInstance()).getString(key, def);
    }

    public static void saveInt(String key, int value) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(App.getInstance()).edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static int getInt(String key) {
        return PreferenceManager.getDefaultSharedPreferences(App.getInstance()).getInt(key,0);
    }

    public static int getInt(String key, int def) {
        return PreferenceManager.getDefaultSharedPreferences(App.getInstance()).getInt(key, def);
    }

}
