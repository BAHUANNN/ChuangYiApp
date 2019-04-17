package com.example.hp.chuangyiapp.net;

import com.example.hp.chuangyiapp.net.bean.LoginBean;
import com.example.hp.chuangyiapp.net.bean.LoginParam;
import com.example.hp.chuangyiapp.net.bean.NewsBean;
import com.example.hp.chuangyiapp.net.bean.NewssBean;
import com.example.hp.chuangyiapp.net.bean.StatesBean;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface RetrofitService {

    //登陆
    @Headers({"Content-Type: application/json"})
    @POST("signup/")
    Observable<Object> signup(@Body RequestBody body);

    @Headers({"Content-Type: application/json"})
    @POST("signin/")
    Observable<LoginBean> signin(@Body RequestBody body);

    //新闻

    @GET("news/list/{page}")
    Observable<NewssBean> getNewsList(@Path("page") int page);

    @GET("news/{news_id}")
    Observable<NewsBean> getNews(@Path("news_id") int news_id);

    @Headers({"Content-Type: application/json"})
    @POST("news/{news_id}/comment/")
    Observable<Object> addComment(@Header("token") String token, @Path("news_id") int news_id, @Body RequestBody body);

    //Feed流
    @Headers({"Content-Type: application/json"})
    @POST("feed/")
    Observable<Object> addState(@Header("token") String token, @Body RequestBody body);

    @GET("feed/list/{page}")
    Observable<StatesBean> getStates(@Path("page") int page);



}
