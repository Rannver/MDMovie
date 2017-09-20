package com.example.rannver.mdmovie.model;

import com.example.rannver.mdmovie.bean.gsonBean.MoiveListGsonBean;
import com.example.rannver.mdmovie.bean.listBean.MoiveListBean;
import com.example.rannver.mdmovie.contract.FutureContract;
import com.example.rannver.mdmovie.retrofit.GetMovieService;
import com.example.rannver.mdmovie.retrofit.MoiveCallback;
import com.example.rannver.mdmovie.retrofit.MovieApi;
import com.example.rannver.mdmovie.retrofit.RetrofitUtil;
import com.example.rannver.mdmovie.webApi.FutureWebApi;
import com.example.rannver.mdmovie.webServce.FutureServce;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rannver on 2017/4/26.
 */

public class FutureModel {

    private FutureContract.FuturePresenter presenter;
    private MoiveListGsonBean gsonBean;
    private List<MoiveListBean> list;

    private String TAG = "FutureModel";

    public FutureModel(FutureContract.FuturePresenter presenter){
        this.presenter = presenter;
        GetFutureInfo();
    }

    //获取即将上映的电影信息（使用新retrofit写法）
    private void GetFutureInfo() {
        GetMovieService getMoiveService = RetrofitUtil.retrofit(MovieApi.MOIVE_API).create(GetMovieService.class);
        Call<MoiveListGsonBean> call = getMoiveService.getFutureList();
        call.enqueue(new MoiveCallback<MoiveListGsonBean>() {
            @Override
            public void onResponse(Call<MoiveListGsonBean> call, Response<MoiveListGsonBean> response) {
                super.onResponse(call, response);
                gsonBean = response.body();
                list = GetListInfo();
                presenter.ModleOK();
            }

            @Override
            public void onFailure(Call<MoiveListGsonBean> call, Throwable t) {
                super.onFailure(call, t);
            }
        });
    }

    //获取即将上映的电影信息（使用旧写法）
    private void GetFutureInfo1() {
        FutureWebApi webApi = new FutureWebApi();
        FutureServce servce = webApi.getServce();
        Call<MoiveListGsonBean> call = servce.getState();
        call.enqueue(new Callback<MoiveListGsonBean>() {
            @Override
            public void onResponse(Call<MoiveListGsonBean> call, Response<MoiveListGsonBean> response) {
                System.out.println("FutureModel:"+call.request().url().toString());
                gsonBean = response.body();
                list = GetListInfo();
                presenter.ModleOK();
            }

            @Override
            public void onFailure(Call<MoiveListGsonBean> call, Throwable t) {
                presenter.ModleFalse();
            }
        });
    }

    private List<MoiveListBean> GetListInfo() {
        List<MoiveListBean> list = new ArrayList<MoiveListBean>();
        List<MoiveListGsonBean.SubjectsBean> subjects = gsonBean.getSubjects();
        for(MoiveListGsonBean.SubjectsBean subject:subjects){

            MoiveListBean moiveBean = new MoiveListBean();
            List<String> directions = new ArrayList<String>();
            List<String> casts = new ArrayList<String>();

            for (MoiveListGsonBean.SubjectsBean.DirectorsBean directior:subject.getDirectors()) {
                directions.add(directior.getName());
            }
            for (MoiveListGsonBean.SubjectsBean.CastsBean cast:subject.getCasts()){
                casts.add(cast.getName());
            }

            moiveBean.setTitle(subject.getTitle());
            moiveBean.setGenres(subject.getGenres());
            moiveBean.setCasts(casts);
            moiveBean.setDirector(directions);
            moiveBean.setUrl(subject.getImages().getLarge());
            moiveBean.setId(subject.getId());
            list.add(moiveBean);
        }
        return list;
    }

    public List<MoiveListBean> GetList(){
        return list;
    }

}
