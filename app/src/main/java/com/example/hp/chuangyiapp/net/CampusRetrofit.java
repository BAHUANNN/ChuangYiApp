package com.example.hp.chuangyiapp.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CampusRetrofit {
    private final RetrofitService mRetrofitService;
    public static final String BASE_URL = "https://whatever";

    public CampusRetrofit() {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(25,TimeUnit.SECONDS)
                .connectTimeout(25, TimeUnit.SECONDS)
                .writeTimeout(25,TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                //http://39.108.79.110:5678/api/
                .build();
        mRetrofitService = retrofit.create(RetrofitService.class);

    }

    public RetrofitService getRetrofitService(){
        return mRetrofitService;
    }
}
