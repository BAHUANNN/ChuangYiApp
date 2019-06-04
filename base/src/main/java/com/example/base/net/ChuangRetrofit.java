package com.example.base.net;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChuangRetrofit {
    private static Map<String,RetrofitService> retrofitServiceMap = new HashMap<>();

    private static RetrofitService createNewRetrofitService(String url) {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(25,TimeUnit.SECONDS)
                .connectTimeout(25, TimeUnit.SECONDS)
                .writeTimeout(25,TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(url)
                .build();
        RetrofitService mRetrofitService = retrofit.create(RetrofitService.class);
        retrofitServiceMap.put(url, mRetrofitService);
        return mRetrofitService;
    }

    public static RetrofitService getRetrofitService(String url){
        RetrofitService retrofitService = retrofitServiceMap.get(url);
        if(retrofitService != null)return retrofitService;
        else return createNewRetrofitService(url);
    }
}
