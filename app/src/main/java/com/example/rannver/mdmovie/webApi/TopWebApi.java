package com.example.rannver.mdmovie.webApi;

import com.example.rannver.mdmovie.webServce.TopServce;

import retrofit2.Retrofit;

/**
 * Created by Rannver on 2017/4/27.
 */

public class TopWebApi extends BaseWebApi {
    Retrofit retrofit = getApi(baseUrl);
    @Override
    public <T> T getServce() {
        return (T) retrofit.create(TopServce.class);
    }
}
