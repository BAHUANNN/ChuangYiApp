package com.example.myaspect.storage;

import com.example.myaspect.bean.InforBean;

import java.util.List;

public class InforStorage {

    private int N;
    private MonitorDBManager manager;

    public InforStorage(){
        N = 0;
        manager = MonitorDBManager.getInstance();
    }

    public int insert(InforBean bean){
        manager.insertInfor(bean);
        return ++N;
    }

    public List<InforBean> load(){
        return manager.loadAllInfor();
    }

    public void delete(){
        manager.deleteAllInfor();
    }



}
