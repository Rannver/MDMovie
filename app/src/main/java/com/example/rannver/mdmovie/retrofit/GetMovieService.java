package com.example.rannver.mdmovie.retrofit;

import com.example.rannver.mdmovie.bean.gsonBean.MoiveListGsonBean;
import com.example.rannver.mdmovie.bean.gsonBean.MoiveDetailGsonBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Rannver on 2017/9/20.
 */

public interface GetMovieService {

    public final static int TOP_TEN = 10;
    public final static int TOP_FIFTY = 50;
    public final static int TOP_MAX = 250;

    @GET("coming_soon")
    Call<MoiveListGsonBean> getFutureList();

    @GET("in_theaters")
    Call<MoiveListGsonBean> getHotList(@Query("city") String city);

    @GET("subject/{id}")
    Call<MoiveDetailGsonBean> getMovieInfo(@Path("id") int id);

    @GET("search")
    Call<MoiveListGsonBean> getSearchResult(@Query("q") String q);

    @GET("top250")
    Call<MoiveListGsonBean> getTopList(@Query("count") int count);

}
