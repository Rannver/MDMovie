package com.example.rannver.mdmovie.model;

import android.util.Log;

import com.example.rannver.mdmovie.bean.gsonBean.MoiveListGsonBean;
import com.example.rannver.mdmovie.bean.listBean.MoiveListBean;
import com.example.rannver.mdmovie.contract.HotContract;
import com.example.rannver.mdmovie.retrofit.GetMovieService;
import com.example.rannver.mdmovie.retrofit.MoiveCallback;
import com.example.rannver.mdmovie.retrofit.MovieApi;
import com.example.rannver.mdmovie.retrofit.RetrofitUtil;
import com.example.rannver.mdmovie.webApi.HotWebApi;
import com.example.rannver.mdmovie.webServce.HotWebServce;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rannver on 2017/4/21.
 */

public class HotModel {

    private MoiveListGsonBean hotGsonBean;
    private String TAG = "HotModel";
    private List<MoiveListBean> hotlist;
    private HotContract.HotPresenter hotPresenter;


    public HotModel(HotContract.HotPresenter hotPresenter){
        this.hotPresenter = hotPresenter;
        GetHotInfo();
    }

    //获取全部当下热门信息(使用新的retrofit写法)
    private void GetHotInfo(){
        GetMovieService getMovieService = RetrofitUtil.retrofit(MovieApi.MOIVE_API).create(GetMovieService.class);
        Call<MoiveListGsonBean> call = getMovieService.getHotList("武汉");
        call.enqueue(new MoiveCallback<MoiveListGsonBean>() {
            @Override
            public void onResponse(Call<MoiveListGsonBean> call, Response<MoiveListGsonBean> response) {
                super.onResponse(call, response);
                hotGsonBean = response.body();
                hotlist = GetHotData();
                hotPresenter.ModleOK();
            }

            @Override
            public void onFailure(Call<MoiveListGsonBean> call, Throwable t) {
                super.onFailure(call, t);
            }
        });
    }

    //获取全部信息(旧的)
    private void GetHotInfo1(){
        Log.d(TAG,"GetHotInfo");
        HotWebApi hotWebApi = new HotWebApi();
        HotWebServce webServce = hotWebApi.getServce();
        Call<MoiveListGsonBean> call = webServce.getState("武汉");
        call.enqueue(new Callback<MoiveListGsonBean>() {
            @Override
            public void onResponse(Call<MoiveListGsonBean> call, final Response<MoiveListGsonBean> response) {
                hotGsonBean = response.body();
                hotlist = GetHotData();
                hotPresenter.ModleOK();
                System.out.println(TAG+":"+response.body().getTitle());
            }

            @Override
            public void onFailure(Call<MoiveListGsonBean> call, Throwable t) {
                Log.d(TAG,"请求信息失败");
            }
        });
    }

    private void LogTAG(){
        if (hotlist==null){
            Log.d(TAG,"hotlist is null");
        }
        if (hotGsonBean==null){
            Log.d(TAG,"hotbean is null");
        }
    }

    //获取最热列表信息
    private List<MoiveListBean> GetHotData(){
        List<MoiveListBean> list = new ArrayList<MoiveListBean>();
        List<MoiveListGsonBean.SubjectsBean> subjects = hotGsonBean.getSubjects();
        for (MoiveListGsonBean.SubjectsBean subject: subjects) {
            MoiveListBean hot = new MoiveListBean();
            List<String> directions = new ArrayList<String>();
            List<String> casts = new ArrayList<String>();

            for (MoiveListGsonBean.SubjectsBean.DirectorsBean directior:subject.getDirectors()) {
                directions.add(directior.getName());
            }
            for (MoiveListGsonBean.SubjectsBean.CastsBean cast:subject.getCasts()){
                casts.add(cast.getName());
            }

            hot.setTitle(subject.getTitle());
            hot.setRate(subject.getRating().getAverage());
            hot.setCollect_count(subject.getCollect_count());
            hot.setUrl(subject.getImages().getLarge());
            hot.setDirector(directions);
            hot.setCasts(casts);
            hot.setId(subject.getId());
            list.add(hot);
        }
        return list;
    }

    //返回hotList
    public List<MoiveListBean> GetHotList(){
        LogTAG();
        return hotlist;
    }

}
