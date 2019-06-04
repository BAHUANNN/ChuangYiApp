package com.example.myaspect.consumer;

import com.example.base.net.ChuangRetrofit;
import com.example.base.net.RetrofitService;

public class MonitorFactory {
    private volatile static RetrofitService sRetrofitService = null;
    private static final String CAMPUS_URL = "http://120.78.194.125:5000/api/v1.0/";

    public static RetrofitService getRetrofitService(){
        RetrofitService retrofitService = sRetrofitService;
        if (retrofitService == null){
            synchronized (MonitorFactory.class){
                retrofitService = sRetrofitService;
                if (retrofitService == null) {
                    sRetrofitService = ChuangRetrofit.getRetrofitService(CAMPUS_URL);
                    retrofitService = sRetrofitService;
                }
            }
        }
        return retrofitService;
    }
}
