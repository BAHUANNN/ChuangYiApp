package com.example.myaspect.storage;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

import com.example.base.Global;

public class MonitorDBHelper extends SQLiteOpenHelper {

    public static MonitorDBHelper instance;

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "monitor.db";

    public static final String INFOR_TABLE_NAME = "infor";


    public static SQLiteDatabase getInstance() {
        if (instance == null) {
            instance = new MonitorDBHelper(Global.getApplication(), DB_NAME, null, DB_VERSION);
        }
        return instance.getReadableDatabase();
    }

    public MonitorDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MonitorDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public MonitorDBHelper(@Nullable Context context, @Nullable String name, int version, @NonNull SQLiteDatabase.OpenParams openParams) {
        super(context, name, version, openParams);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTbStr = "create table if not exists "+ INFOR_TABLE_NAME +"( _id integer primary key, name varchar, age integer, sex varchar)";
        db.execSQL(createTbStr);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+ INFOR_TABLE_NAME);
        onCreate(db);
    }
}
