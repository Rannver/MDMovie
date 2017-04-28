package com.example.rannver.mdmovie.webApi;

import com.example.rannver.mdmovie.webServce.MoiveDetailServce;

import retrofit2.Retrofit;

/**
 * Created by Rannver on 2017/4/28.
 */

public class MoiveDetailWebApi extends BaseWebApi {

    Retrofit retrofit = getApi(baseUrl);
    @Override
    public <T> T getServce() {
        return (T) retrofit.create(MoiveDetailServce.class);
    }
}
