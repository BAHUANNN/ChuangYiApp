package com.example.myaspect.storage;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myaspect.bean.InforBean;

import java.util.ArrayList;
import java.util.List;

public class MonitorDBManager {
    private static MonitorDBManager instance = null;
    private SQLiteDatabase db = MonitorDBHelper.getInstance();

    public static MonitorDBManager getInstance(){
        if(instance == null){
            instance = new MonitorDBManager();
        }
        return instance;
    }

    public void insertInfor(InforBean bean){
        db.execSQL("INSERT INTO " + MonitorDBHelper.INFOR_TABLE_NAME + " VALUES(null,?)",
                new String[]{
                   bean.toString()
                });
    }

    public List<InforBean> loadAllInfor(){
        List<InforBean> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + MonitorDBHelper.INFOR_TABLE_NAME + " ", null);
        if(cursor.getCount() > 0){
            InforBean bean = new InforBean();
            //todo
            list.add(bean);
        }
        return list;
    }

    public void deleteAllInfor(){
        db.execSQL("delete from " + MonitorDBHelper.INFOR_TABLE_NAME + ";");
    }

    public boolean isRepetetive(){
        return false;
    }

}
