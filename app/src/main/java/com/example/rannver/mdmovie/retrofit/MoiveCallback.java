package com.example.rannver.mdmovie.retrofit;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rannver on 2017/9/20.
 */

public abstract class MoiveCallback<T> implements Callback<T> {

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        System.out.println("MoiveCallback: onResponse "+call.request().url().toString());
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        System.out.println("MoiveCallback:  onFailure "+call.request().url().toString());
        System.out.println("MoiveCallback:  onFailure throwable:"+t.toString());
    }
}
