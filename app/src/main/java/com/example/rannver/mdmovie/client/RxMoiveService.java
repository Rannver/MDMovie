package com.example.rannver.mdmovie.client;

import com.example.rannver.mdmovie.bean.gsonBean.MoiveDetailGsonBean;
import com.example.rannver.mdmovie.bean.gsonBean.MoiveListGsonBean;
import io.reactivex.Observable;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Rannver on 2017/9/28.
 */

public interface RxMoiveService {

    public final static int TOP_TEN = 10;
    public final static int TOP_FIFTY = 50;
    public final static int TOP_MAX = 250;

    @GET("coming_soon")
    Observable<MoiveListGsonBean> getFutureList();

    @GET("in_theaters")
    Observable<MoiveListGsonBean> getHotList(@Query("city") String city);

    @GET("subject/{id}")
    Observable<MoiveDetailGsonBean> getMovieInfo(@Path("id") int id);

    @GET("search")
    Observable<MoiveListGsonBean> getSearchResult(@Query("q") String q);

    @GET("top250")
    Observable<MoiveListGsonBean> getTopList(@Query("count") int count);

}
