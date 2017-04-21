package com.example.rannver.mdmovie.webApi;

import com.example.rannver.mdmovie.webServce.HotWebServce;

import retrofit2.Retrofit;

/**
 * Created by Rannver on 2017/4/21.
 */

public class HotWebApi extends BaseWebApi {

    Retrofit retrofit = getApi(baseUrl);

    @Override
    public <T> T getServce() {
        return (T) retrofit.create(HotWebServce.class);
    }
}
