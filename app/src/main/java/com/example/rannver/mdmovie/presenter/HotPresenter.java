package com.example.rannver.mdmovie.presenter;

import com.example.rannver.mdmovie.contract.HotContract;
import com.example.rannver.mdmovie.model.HotModel;

/**
 * Created by Rannver on 2017/4/21.
 */

public class HotPresenter implements HotContract.HotPresenter {

    private HotModel hotModel;
    private HotContract.HotView hotView;

    public HotPresenter(HotContract.HotView hotView){
        this.hotView = hotView;
        hotModel= new HotModel();
    }

    @Override
    public void start() {

    }


}
