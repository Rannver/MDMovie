package com.example.rannver.mdmovie.presenter;

import android.util.Log;

import com.example.rannver.mdmovie.bean.listBean.HotListBean;
import com.example.rannver.mdmovie.contract.HotContract;
import com.example.rannver.mdmovie.model.HotModel;

import java.util.List;

/**
 * Created by Rannver on 2017/4/21.
 */

public class HotPresenter implements HotContract.HotPresenter {

    private String TAG = "HotPresenter";

    private HotModel hotModel;
    private HotContract.HotView hotView;
    private List<HotListBean> hotlist;

    public HotPresenter(){
        hotModel= new HotModel(HotPresenter.this);
    }

    @Override
    public void start() {
        Log.d(TAG,"start()");
    }


    private void SetListData(){
        hotView.SetHotList(hotlist);
        hotView.initList();
    }


    @Override
    public void initView(HotContract.HotView hotView) {
        Log.d(TAG,"initView()");
        this.hotView = hotView;
    }

    @Override
    public void ModleOK() {
        hotlist = hotModel.GetHotList();
        SetListData();
    }
}
