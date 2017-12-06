package com.example.rannver.mdmovie.retrofit;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Rannver on 2017/9/20.
 */

public class RetrofitUtil {

    private static final String TAG = "RetrofitUtil";


    private static OkHttpClient getOkHttpCilent(){

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


    public static Retrofit retrofit(String baseUrl){

        return  new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(getOkHttpCilent())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
