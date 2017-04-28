package com.example.rannver.mdmovie.presenter;

import com.example.rannver.mdmovie.bean.listBean.MoiveListBean;
import com.example.rannver.mdmovie.contract.FutureContract;
import com.example.rannver.mdmovie.model.FutureModel;

import java.util.List;

/**
 * Created by Rannver on 2017/4/26.
 */

public class FuturePresenter implements FutureContract.FuturePresenter {

    private String TAG = "FuturePresenter";
    private FutureModel model;
    private FutureContract.FutureView view;
    private List<MoiveListBean> list;

    public FuturePresenter(){
        model = new FutureModel(FuturePresenter.this);
    }

    @Override
    public void start() {

    }


    @Override
    public void initView(FutureContract.FutureView futureView) {
        view = futureView;
    }

    @Override
    public void ModleOK() {
        list = model.GetList();
        view.initList(list);
    }

    @Override
    public void ModleFalse() {
        view.initText();
    }
}
