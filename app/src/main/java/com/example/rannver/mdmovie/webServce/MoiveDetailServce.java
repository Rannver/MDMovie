package com.example.rannver.mdmovie.webServce;

import com.example.rannver.mdmovie.bean.gsonBean.MoiveDetailGsonBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Rannver on 2017/4/28.
 */

public interface MoiveDetailServce {
    @GET("subject/{id}")
    Call<MoiveDetailGsonBean> getState(@Path("id") int id);
}
