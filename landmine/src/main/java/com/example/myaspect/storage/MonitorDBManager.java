package com.example.myaspect.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class MonitorDBManager {
    private static MonitorDBManager instance = null;
    private SQLiteDatabase mDb = null;
    private MonitorDBHelper mDbHelper = null;

    public static MonitorDBManager getInstance(){
        if(instance == null){
            instance = new MonitorDBManager();
        }
        return instance;
    }

}
