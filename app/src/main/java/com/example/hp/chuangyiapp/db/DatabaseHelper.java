package com.example.hp.chuangyiapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {


    private static DatabaseHelper instance;

    private static final String DB_NAME = "chuangyi_db";
    private static final int DB_VERSION = 1;

    //homeFragment搜索的历史记录
    public static final String SEARCH_HISTORY = "search_history";
    public static final String KEY_ID = "id";
    public static final String KEY_QUERY = "key_query";



    public DatabaseHelper(@Nullable Context context,@Nullable String name,@Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static SQLiteDatabase getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context, DB_NAME, null, DB_VERSION);
        }
        return instance.getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createSearchHistory = "CREATE TABLE IF NOT EXISTS " + SEARCH_HISTORY +
                "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_QUERY + " TEXT);";
        db.execSQL(createSearchHistory);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
