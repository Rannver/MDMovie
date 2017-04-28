package com.example.rannver.mdmovie.model;

import com.example.rannver.mdmovie.bean.gsonBean.HotGsonBean;
import com.example.rannver.mdmovie.bean.listBean.MoiveListBean;
import com.example.rannver.mdmovie.contract.FutureContract;
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
    private HotGsonBean gsonBean;
    private List<MoiveListBean> list;

    private String TAG = "FutureModel";

    public FutureModel(FutureContract.FuturePresenter presenter){
        this.presenter = presenter;
        GetFutureInfo();
    }

    private void GetFutureInfo() {
        FutureWebApi webApi = new FutureWebApi();
        FutureServce servce = webApi.getServce();
        Call<HotGsonBean> call = servce.getState();
        call.enqueue(new Callback<HotGsonBean>() {
            @Override
            public void onResponse(Call<HotGsonBean> call, Response<HotGsonBean> response) {
                gsonBean = response.body();
                list = GetListInfo();
                presenter.ModleOK();
            }

            @Override
            public void onFailure(Call<HotGsonBean> call, Throwable t) {
                presenter.ModleFalse();
            }
        });
    }

    private List<MoiveListBean> GetListInfo() {
        List<MoiveListBean> list = new ArrayList<MoiveListBean>();
        List<HotGsonBean.SubjectsBean> subjects = gsonBean.getSubjects();
        for(HotGsonBean.SubjectsBean subject:subjects){

            MoiveListBean moiveBean = new MoiveListBean();
            List<String> directions = new ArrayList<String>();
            List<String> casts = new ArrayList<String>();

            for (HotGsonBean.SubjectsBean.DirectorsBean directior:subject.getDirectors()) {
                directions.add(directior.getName());
            }
            for (HotGsonBean.SubjectsBean.CastsBean cast:subject.getCasts()){
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
