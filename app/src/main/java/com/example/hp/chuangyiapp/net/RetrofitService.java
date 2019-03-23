package com.example.hp.chuangyiapp.net;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitService {
    @GET("top250")
    Call<Object> getTop250(@Query("start") int start, @Query("count")int count);

}
