package com.example.rannver.mdmovie.client;

import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Rannver on 2017/9/28.
 */

public class MoiveClient {

    private static MoiveClient moiveClient;

    private MoiveClient(){}

    public static MoiveClient getInstance(){
        if (moiveClient==null){
            synchronized (MoiveClient.class){
                if (moiveClient==null){
                    moiveClient = new MoiveClient();
                    Log.d("MoiveClient","MoiveClient create");
                }
            }
        }
        return moiveClient;
    }

    //retrofit部分
    public <T> T create(Class<T> service,String baseUrl){
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(service);
    }

}
