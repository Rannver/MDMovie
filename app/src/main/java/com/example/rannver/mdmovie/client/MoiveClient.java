package com.example.rannver.mdmovie.client;

import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Rannver on 2017/9/28.
 */

public class MoiveClient {

    private static String TAG = "MoiveClient";
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
                .client(getOkHttpCilent())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(service);

    }


    //打印日志
    private  OkHttpClient getOkHttpCilent(){

        Log.d(TAG,"");

        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d(TAG,message);
            }
        });
        loggingInterceptor.setLevel(level);
        OkHttpClient.Builder bulider = new OkHttpClient.Builder();
        bulider.addNetworkInterceptor(loggingInterceptor);

        return bulider.build();
    }

}
