package com.example.rannver.mdmovie.webApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Rannver on 2017/4/21.
 */

public  abstract class BaseWebApi {

    public String baseUrl = "http://api.douban.com/v2/movie/";

    Retrofit getApi(String url){
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public abstract <T> T getServce();
}
