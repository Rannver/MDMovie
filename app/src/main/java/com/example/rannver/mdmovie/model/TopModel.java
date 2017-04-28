package com.example.rannver.mdmovie.model;

import android.util.Log;

import com.example.rannver.mdmovie.bean.gsonBean.HotGsonBean;
import com.example.rannver.mdmovie.bean.listBean.MoiveListBean;
import com.example.rannver.mdmovie.contract.TopContract;
import com.example.rannver.mdmovie.webApi.TopWebApi;
import com.example.rannver.mdmovie.webServce.TopServce;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rannver on 2017/4/27.
 */

public class TopModel {

    private String TAG = "TopModel";

    private TopContract.TopPresenter presenter;
    private List<MoiveListBean> list;
    private HotGsonBean gsonBean;

    public TopModel(TopContract.TopPresenter presenter){
        this.presenter = presenter;
        GetTopInfo();
    }

    private void GetTopInfo() {
        TopWebApi webApi = new TopWebApi();
        TopServce servce = webApi.getServce();
        Call<HotGsonBean> call = servce.getState(TopServce.TOP_FIFTY);
        call.enqueue(new Callback<HotGsonBean>() {
            @Override
            public void onResponse(Call<HotGsonBean> call, Response<HotGsonBean> response) {
                gsonBean = response.body();
                list = GetListData();
                presenter.ModleOK();
            }

            @Override
            public void onFailure(Call<HotGsonBean> call, Throwable t) {
                presenter.ModleFalse();
            }
        });
    }

    private List<MoiveListBean> GetListData() {
        List<MoiveListBean> list = new ArrayList<>();
        List<HotGsonBean.SubjectsBean> subjects = gsonBean.getSubjects();
        for (HotGsonBean.SubjectsBean subject: subjects) {
            MoiveListBean top = new MoiveListBean();
            List<String> directions = new ArrayList<String>();
            List<String> casts = new ArrayList<String>();

            for (HotGsonBean.SubjectsBean.DirectorsBean directior:subject.getDirectors()) {
                directions.add(directior.getName());
            }
            for (HotGsonBean.SubjectsBean.CastsBean cast:subject.getCasts()){
                casts.add(cast.getName());
            }

            top.setTitle(subject.getTitle());
            top.setRate(subject.getRating().getAverage());
            top.setCollect_count(subject.getCollect_count());
            top.setUrl(subject.getImages().getLarge());
            top.setDirector(directions);
            top.setCasts(casts);
            top.setId(subject.getId());
            list.add(top);
        }
        return list;
    }

    public List<MoiveListBean> getList() {
        return list;
    }
}
