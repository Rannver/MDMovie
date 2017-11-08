package com.example.rannver.mdmovie.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Rannver on 2017/9/20.
 */

public class RetrofitUtil {


    public static Retrofit retrofit(String baseUrl){
        return  new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
