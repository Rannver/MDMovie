package com.example.rannver.mdmovie.webServce;

import com.example.rannver.mdmovie.bean.gsonBean.HotGsonBean;
import com.example.rannver.mdmovie.bean.listBean.MoiveListBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Rannver on 2017/4/27.
 */

public interface TopServce {

    public final static int TOP_TEN = 10;
    public final static int TOP_FIFTY = 50;


    @GET("top250")
    Call<HotGsonBean> getState(@Query("count") int count);

}
