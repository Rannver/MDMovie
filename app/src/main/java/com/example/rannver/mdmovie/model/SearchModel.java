package com.example.rannver.mdmovie.model;

import com.example.rannver.mdmovie.bean.gsonBean.MoiveListGsonBean;
import com.example.rannver.mdmovie.bean.listBean.MoiveListBean;
import com.example.rannver.mdmovie.client.MoiveClient;
import com.example.rannver.mdmovie.client.RxMoiveService;
import com.example.rannver.mdmovie.contract.SearchContract;
import com.example.rannver.mdmovie.retrofit.GetMovieService;
import com.example.rannver.mdmovie.retrofit.MoiveCallback;
import com.example.rannver.mdmovie.retrofit.MovieApi;
import com.example.rannver.mdmovie.retrofit.RetrofitUtil;
import com.example.rannver.mdmovie.webApi.SearchWebApi;
import com.example.rannver.mdmovie.webServce.SearchServce;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rannver on 2017/4/28.
 */

public class SearchModel {

    private String TAG = "SearchModel";
    private SearchContract.SearchPresenter presenter;
    private List<MoiveListBean> list;


    private MoiveListGsonBean gsonBean;

    public SearchModel(SearchContract.SearchPresenter presenter){
        this.presenter = presenter;
    }

    public void SetQuery(String q){
        GetSearchInfo(q);
    }

    private void RxGetSearchInfo(String q){
        RxMoiveService rxMoiveService = MoiveClient.getInstance().create(RxMoiveService.class,MovieApi.MOIVE_API);
        rxMoiveService.getSearchResult(q)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MoiveListGsonBean>() {
                    @Override
                    public void accept(MoiveListGsonBean moiveListGsonBean) throws Exception {
                        if (moiveListGsonBean.getTotal()!=0){
                            gsonBean = moiveListGsonBean;
                            list = GetListData();
                            presenter.ModleOK();
                        }else {
                            presenter.ModleNull();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        presenter.ModleFalse();
                    }
                });
    }

    //搜索结果显示（新retrofit）
    private void GetSearchInfo(String q) {
        GetMovieService getMoiveService = RetrofitUtil.retrofit(MovieApi.MOIVE_API).create(GetMovieService.class);
        Call<MoiveListGsonBean> call = getMoiveService.getSearchResult(q);
        call.enqueue(new MoiveCallback<MoiveListGsonBean>() {
            @Override
            public void onResponse(Call<MoiveListGsonBean> call, Response<MoiveListGsonBean> response) {
                super.onResponse(call, response);
                if (response.body().getTotal()!=0){
                    gsonBean = response.body();
                    list = GetListData();
                    presenter.ModleOK();
                }else {
                    presenter.ModleNull();
                }
            }

            @Override
            public void onFailure(Call<MoiveListGsonBean> call, Throwable t) {
                super.onFailure(call, t);
                presenter.ModleFalse();
            }
        });
    }

    private List<MoiveListBean> GetListData() {
        List<MoiveListBean> list = new ArrayList<>();
        List<MoiveListGsonBean.SubjectsBean> subjects = gsonBean.getSubjects();
        for (MoiveListGsonBean.SubjectsBean subject: subjects) {
            MoiveListBean top = new MoiveListBean();
            List<String> directions = new ArrayList<String>();
            List<String> casts = new ArrayList<String>();

            for (MoiveListGsonBean.SubjectsBean.DirectorsBean directior:subject.getDirectors()) {
                directions.add(directior.getName());
            }
            for (MoiveListGsonBean.SubjectsBean.CastsBean cast:subject.getCasts()){
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
