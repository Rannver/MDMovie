package com.example.rannver.mdmovie.contract;

import com.example.rannver.mdmovie.BasePresenter;
import com.example.rannver.mdmovie.BaseView;
import com.example.rannver.mdmovie.bean.listBean.MoiveListBean;

import java.util.List;

/**
 * Created by Rannver on 2017/4/27.
 */

public interface TopContract {

    interface TopView extends BaseView<TopPresenter> {

        void initList(List<MoiveListBean> list);

        void initText();

    }

    interface TopPresenter extends BasePresenter {

        void initView(TopView topView);

        void  ModleOK();

        void  ModleFalse();
    }

}
