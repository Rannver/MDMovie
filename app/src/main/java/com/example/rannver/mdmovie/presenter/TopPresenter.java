package com.example.rannver.mdmovie.presenter;

import com.example.rannver.mdmovie.bean.listBean.MoiveListBean;
import com.example.rannver.mdmovie.contract.TopContract;
import com.example.rannver.mdmovie.model.TopModel;

import java.util.List;

/**
 * Created by Rannver on 2017/4/27.
 */

public class TopPresenter implements TopContract.TopPresenter {

    private String TAG = "TopPresenter";

    private TopModel model;
    private TopContract.TopView view;
    private List<MoiveListBean> list;

    public TopPresenter(){
        model = new TopModel(TopPresenter.this);
    }

    @Override
    public void start() {

    }

    @Override
    public void initView(TopContract.TopView topView) {
        view = topView;
    }

    @Override
    public void ModleOK() {
        list = model.getList();
        view.initList(list);
    }

    @Override
    public void ModleFalse() {
        view.initText();
    }
}
