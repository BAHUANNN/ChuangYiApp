package com.example.hp.chuangyiapp.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ChuangyiDao {
    private SQLiteDatabase db;

    public ChuangyiDao(Context context){
        db = DatabaseHelper.getInstance(context);
    }

    public void insertSearchHistory(String query) {
        db.execSQL("INSERT INTO " + DatabaseHelper.SEARCH_HISTORY +
                        " VALUES(NULL,?)",
                new String[]{query});

    }

    public List<String> loadSearchHistory() {
        List<String> records = new ArrayList<>();
        Cursor cursor;
        cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.SEARCH_HISTORY +
                        " ORDER BY " + DatabaseHelper.KEY_ID + " DESC ",null);
        if (cursor.getCount() > 0) {
            int i = 0;
            while (cursor.moveToNext()) {
                records.add(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_QUERY)));
                i++;
            }
        }
        if (cursor != null) {
            cursor.close();
        }
        return records;
    }

    public void deleteAllHistory() {
        db.execSQL("DELETE FROM " + DatabaseHelper.SEARCH_HISTORY);
    }
}
