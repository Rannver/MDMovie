package com.example.rannver.mdmovie.webApi;


import com.example.rannver.mdmovie.webServce.SearchServce;

import retrofit2.Retrofit;

/**
 * Created by Rannver on 2017/4/28.
 */

public class SearchWebApi extends BaseWebApi {

    Retrofit retrofit = getApi(baseUrl);

    @Override
    public <T> T getServce() {
        return (T) retrofit.create(SearchServce.class);
    }
}
