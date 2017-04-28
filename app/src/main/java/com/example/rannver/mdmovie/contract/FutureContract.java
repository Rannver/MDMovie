package com.example.rannver.mdmovie.contract;

import com.example.rannver.mdmovie.BasePresenter;
import com.example.rannver.mdmovie.BaseView;
import com.example.rannver.mdmovie.bean.listBean.MoiveListBean;

import java.util.List;

/**
 * Created by Rannver on 2017/4/26.
 */

public interface FutureContract {

    interface FutureView extends BaseView<FuturePresenter> {

        void initList(List<MoiveListBean> list);

        void initText();

    }

    interface FuturePresenter extends BasePresenter {

        void initView(FutureView futureView);

        void ModleOK();

        void ModleFalse();

    }

}
