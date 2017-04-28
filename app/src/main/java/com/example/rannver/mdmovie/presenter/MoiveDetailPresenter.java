package com.example.rannver.mdmovie.presenter;

import com.example.rannver.mdmovie.bean.listBean.CastListBean;
import com.example.rannver.mdmovie.contract.HotContract;
import com.example.rannver.mdmovie.contract.MoiveDetailContract;
import com.example.rannver.mdmovie.model.MoiveDetailModel;

import java.util.List;

/**
 * Created by Rannver on 2017/4/21.
 */

public class MoiveDetailPresenter  implements MoiveDetailContract.DetailPresenter {

    private String TAG = "MoiveDetailPresenter";
    private MoiveDetailModel moiveDetailModel;
    private MoiveDetailContract.DetailView detailView;
    private List<CastListBean> casts,directors;

    public MoiveDetailPresenter(){
        moiveDetailModel  = new MoiveDetailModel(MoiveDetailPresenter.this);
    }

    @Override
    public void start() {

    }

    @Override
    public void initView(MoiveDetailContract.DetailView detailView) {
        this.detailView = detailView;
    }

    @Override
    public void getMoiveDetail(int id) {
        moiveDetailModel.SetMoiveId(id);
    }

    @Override
    public void ModleOK() {
        casts = moiveDetailModel.getCasts();
        directors = moiveDetailModel.getDirectors();
        detailView.initList(directors,casts);
        detailView.initDetail(moiveDetailModel.getDetailBean());
        detailView.initRating(moiveDetailModel.GetStar());
    }

    @Override
    public void ModleFalse() {
        detailView.initText();
    }
}
