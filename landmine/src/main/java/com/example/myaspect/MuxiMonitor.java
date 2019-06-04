package com.example.myaspect;

import android.content.Context;
import android.telephony.TelephonyManager;

import com.example.myaspect.broker.InforBroker;

public class MuxiMonitor {

    private String deviceId = "";
    private String studentId = "";
    private InforBroker inforBroker = new InforBroker();

    private volatile static MuxiMonitor instance = null;

    public MuxiMonitor() {
    }

    public static MuxiMonitor getMonitor() {
        if (instance == null) {
            synchronized (MuxiMonitor.class) {
                if (instance == null) {
                    instance = new MuxiMonitor();
                }
            }
        }
        return instance;
    }

    //在application执行初始化
    public static void init(String deviceId, String url) {
        instance = getMonitor();
        instance.deviceId = deviceId;
        instance.inforBroker.setUrl(url);

    }

    //在mainActivity检测登陆状态
    public static void login(String studentId) {
        getMonitor().studentId = studentId;
    }

}
