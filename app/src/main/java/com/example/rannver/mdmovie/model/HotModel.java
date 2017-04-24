package com.example.rannver.mdmovie.model;

import android.os.Handler;
import android.util.Log;

import com.example.rannver.mdmovie.bean.gsonBean.HotGsonBean;
import com.example.rannver.mdmovie.bean.listBean.HotListBean;
import com.example.rannver.mdmovie.contract.HotContract;
import com.example.rannver.mdmovie.presenter.HotPresenter;
import com.example.rannver.mdmovie.webApi.HotWebApi;
import com.example.rannver.mdmovie.webServce.HotWebServce;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.StreamHandler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rannver on 2017/4/21.
 */

public class HotModel {

    private HotGsonBean hotGsonBean;
    private String TAG = "HotModel";
    private List<HotListBean> hotlist;
    private HotContract.HotPresenter hotPresenter;


    public HotModel(HotContract.HotPresenter hotPresenter){
        this.hotPresenter = hotPresenter;
        GetHotInfo();
    }

    //获取全部信息
    private void GetHotInfo(){
        Log.d(TAG,"GetHotInfo");
        HotWebApi hotWebApi = new HotWebApi();
        HotWebServce webServce = hotWebApi.getServce();
        Call<HotGsonBean> call = webServce.getState("武汉");
        call.enqueue(new Callback<HotGsonBean>() {
            @Override
            public void onResponse(Call<HotGsonBean> call, final Response<HotGsonBean> response) {
                hotGsonBean = response.body();
                hotlist = GetHotData();
                hotPresenter.ModleOK();
                System.out.println(TAG+":"+response.body().getTitle());
            }

            @Override
            public void onFailure(Call<HotGsonBean> call, Throwable t) {
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
    private List<HotListBean> GetHotData(){
        List<HotListBean> list = new ArrayList<HotListBean>();
        List<HotGsonBean.SubjectsBean> subjects = hotGsonBean.getSubjects();
        for (HotGsonBean.SubjectsBean subject: subjects) {
            HotListBean hot = new HotListBean();
            List<String> directions = new ArrayList<String>();
            List<String> casts = new ArrayList<String>();

            for (HotGsonBean.SubjectsBean.DirectorsBean directior:subject.getDirectors()) {
                directions.add(directior.getName());
            }
            for (HotGsonBean.SubjectsBean.CastsBean cast:subject.getCasts()){
                casts.add(cast.getName());
            }

            hot.setTitle(subject.getTitle());
            hot.setRate(subject.getRating().getAverage());
            hot.setCollect_count(subject.getCollect_count());
            hot.setUrl(subject.getImages().getSmall());
            hot.setDirector(directions);
            hot.setCasts(casts);
            list.add(hot);
        }
        return list;
    }

    //返回hotList
    public List<HotListBean> GetHotList(){
        LogTAG();
        return hotlist;
    }

}
