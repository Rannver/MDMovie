package com.example.rannver.mdmovie.webServce;

import com.example.rannver.mdmovie.bean.gsonBean.HotGsonBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Rannver on 2017/4/28.
 */

public interface SearchServce {

    @GET("search")
    Call<HotGsonBean> getState(@Query("q") String q);

}
