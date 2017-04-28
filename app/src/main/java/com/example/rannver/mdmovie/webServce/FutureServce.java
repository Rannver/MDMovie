package com.example.rannver.mdmovie.webServce;

import com.example.rannver.mdmovie.bean.gsonBean.HotGsonBean;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Rannver on 2017/4/27.
 */

public interface FutureServce {

    @GET("coming_soon")
    Call<HotGsonBean> getState();
}
