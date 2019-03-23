package com.example.hp.chuangyiapp.net;

public class CampusFactory {

    private volatile static RetrofitService sRetrofitService = null;

    public static RetrofitService getRetrofitService(){
        RetrofitService retrofitService = sRetrofitService;
        if (retrofitService == null){
            synchronized (CampusFactory.class){
                retrofitService = sRetrofitService;
                if (retrofitService == null) {
                    sRetrofitService = new CampusRetrofit().getRetrofitService();
                    retrofitService = sRetrofitService;
                }
            }
        }
        return retrofitService;
    }


}
