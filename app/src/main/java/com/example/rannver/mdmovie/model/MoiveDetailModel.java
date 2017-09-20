package com.example.rannver.mdmovie.model;

import com.example.rannver.mdmovie.bean.gsonBean.MoiveDetailGsonBean;
import com.example.rannver.mdmovie.bean.listBean.CastListBean;
import com.example.rannver.mdmovie.bean.listBean.MoiveDetailBean;
import com.example.rannver.mdmovie.contract.MoiveDetailContract;
import com.example.rannver.mdmovie.retrofit.GetMovieService;
import com.example.rannver.mdmovie.retrofit.MoiveCallback;
import com.example.rannver.mdmovie.retrofit.MovieApi;
import com.example.rannver.mdmovie.retrofit.RetrofitUtil;
import com.example.rannver.mdmovie.webApi.MoiveDetailWebApi;
import com.example.rannver.mdmovie.webServce.MoiveDetailServce;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rannver on 2017/4/28.
 */

public class MoiveDetailModel {

    private String TAG = "MoiveDetailModel";

    private MoiveDetailContract.DetailPresenter presenter;
    private List<CastListBean> casts,directors;
    private MoiveDetailBean detailBean;

    public MoiveDetailModel(MoiveDetailContract.DetailPresenter presenter){
        this.presenter = presenter;
    }

    public void SetMoiveId(int id){
        GetMoiveInfo(id);
    }

    //获取电影详细信息（新retrofit）
    private void GetMoiveInfo(int id) {
        GetMovieService getMoiveService = RetrofitUtil.retrofit(MovieApi.MOIVE_API).create(GetMovieService.class);
        Call<MoiveDetailGsonBean> call = getMoiveService.getMovieInfo(id);
        call.enqueue(new MoiveCallback<MoiveDetailGsonBean>() {
            @Override
            public void onResponse(Call<MoiveDetailGsonBean> call, Response<MoiveDetailGsonBean> response) {
                super.onResponse(call,response);
                GetListInfo(response.body().getDirectors(),response.body().getCasts());
                detailBean = GetBeanInfo(response.body());
                presenter.ModleOK();
            }

            @Override
            public void onFailure(Call<MoiveDetailGsonBean> call, Throwable t) {
                super.onFailure(call, t);
                presenter.ModleFalse();
            }
        });
    }

    //获取电影详细信息（旧retrofit）
    private void GetMoiveInfo1(int id) {
        System.out.println(TAG+":"+id);
        MoiveDetailWebApi webApi = new MoiveDetailWebApi();
        MoiveDetailServce servce = webApi.getServce();
        Call<MoiveDetailGsonBean> call = servce.getState(id);
        call.enqueue(new Callback<MoiveDetailGsonBean>() {
            @Override
            public void onResponse(Call<MoiveDetailGsonBean> call, Response<MoiveDetailGsonBean> response) {
                GetListInfo(response.body().getDirectors(),response.body().getCasts());
                detailBean = GetBeanInfo(response.body());
                presenter.ModleOK();
            }

            @Override
            public void onFailure(Call<MoiveDetailGsonBean> call, Throwable t) {
                presenter.ModleFalse();
            }
        });
    }

    private MoiveDetailBean GetBeanInfo(MoiveDetailGsonBean body) {
        MoiveDetailBean bean = new MoiveDetailBean();
        bean.setUrl(body.getImages().getLarge());
        bean.setAvgRate(body.getRating().getAverage());
        bean.setCount(body.getCollect_count());
        bean.setYear(body.getYear());
        bean.setIntroduce(body.getSummary());
        bean.setTitle(body.getTitle());

        String countries = "";
        for(int i = 0;i<body.getCountries().size();i++){
            String s = body.getCountries().get(i);
            if(i!=0){
                countries += "/"+s;
            }else {
                countries += s;
            }
        }
        bean.setCountries(countries);

        String gener = "";
        for(int i = 0;i<body.getGenres().size();i++){
            String s = body.getGenres().get(i);
            if(i!=0){
                gener += "/"+s;
            }else {
                gener += s;
            }
        }
        bean.setGenres(gener);

        return bean;
    }

    private void GetListInfo(List<MoiveDetailGsonBean.DirectorsBean> directors, List<MoiveDetailGsonBean.CastsBean> casts) {
        List<CastListBean> list_d = new ArrayList<CastListBean>();
        List<CastListBean> list_c = new ArrayList<CastListBean>();

        for(MoiveDetailGsonBean.DirectorsBean d:directors){
            CastListBean bean = new CastListBean();
            if (d.getAvatars()!=null) {
                bean.setUrl(d.getAvatars().getMedium());
            }
            bean.setName(d.getName());
            list_d.add(bean);
        }

        for(MoiveDetailGsonBean.CastsBean c:casts){
            CastListBean bean = new CastListBean();
            bean.setName(c.getName());
            if (c.getAvatars()!=null){
                bean.setUrl(c.getAvatars().getMedium());
            }
            list_c.add(bean);
        }

        this.casts = list_c;
        this.directors = list_d;

    }

    public List<CastListBean> getCasts() {
        return casts;
    }

    public List<CastListBean> getDirectors() {
        return directors;
    }

    public MoiveDetailBean getDetailBean() {
        return detailBean;
    }

    public int GetStar(){
        return (int) (detailBean.getAvgRate()* 5 / 10);
    }
}
