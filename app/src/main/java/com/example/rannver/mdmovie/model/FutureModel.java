package com.example.rannver.mdmovie.model;

import android.util.Log;

import com.example.rannver.mdmovie.bean.gsonBean.MoiveListGsonBean;
import com.example.rannver.mdmovie.bean.listBean.MoiveListBean;
import com.example.rannver.mdmovie.client.MoiveClient;
import com.example.rannver.mdmovie.client.RxMoiveService;
import com.example.rannver.mdmovie.contract.FutureContract;
import com.example.rannver.mdmovie.retrofit.GetMovieService;
import com.example.rannver.mdmovie.retrofit.MoiveCallback;
import com.example.rannver.mdmovie.retrofit.MovieApi;
import com.example.rannver.mdmovie.retrofit.RetrofitUtil;
import com.example.rannver.mdmovie.webApi.FutureWebApi;
import com.example.rannver.mdmovie.webServce.FutureServce;


import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
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
        RxGetFutureInfo();
    }

    //rxjava+retrofit进行网络访问
    private void RxGetFutureInfo(){
        RxMoiveService rxMoiveService = MoiveClient.getInstance().create(RxMoiveService.class,MovieApi.MOIVE_API);
        rxMoiveService.getFutureList()
                .subscribeOn(Schedulers.newThread())     //指定subscribe()发生在线程
                .observeOn(AndroidSchedulers.mainThread())   ///指定回调发生在主线程
                .subscribe(new Observer<MoiveListGsonBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(MoiveListGsonBean value) {
                        Log.d(TAG, "onNext");
                        gsonBean = value;
                        list = GetListInfo();
                        presenter.ModleOK();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete");
                    }
                });
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
                presenter.ModleFalse();
            }
        });
    }

    //旧retrifot写法
    private void GetFutureInfo2(){
        FutureWebApi webApi = new FutureWebApi();
        FutureServce servce = webApi.getServce();
        Call<MoiveListGsonBean> call = servce.getState();
        call.enqueue(new Callback<MoiveListGsonBean>() {
            @Override
            public void onResponse(Call<MoiveListGsonBean> call, Response<MoiveListGsonBean> response) {
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
