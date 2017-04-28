package com.example.rannver.mdmovie.webApi;

import com.example.rannver.mdmovie.webServce.FutureServce;

import retrofit2.Retrofit;

/**
 * Created by Rannver on 2017/4/27.
 */

public class FutureWebApi extends BaseWebApi {

    Retrofit retrofit = getApi(baseUrl);

    @Override
    public <T> T getServce() {
        return (T) retrofit.create(FutureServce.class);
    }
}
