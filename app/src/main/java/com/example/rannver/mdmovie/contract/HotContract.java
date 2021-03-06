package com.example.rannver.mdmovie.contract;

import com.example.rannver.mdmovie.BasePresenter;
import com.example.rannver.mdmovie.BaseView;
import com.example.rannver.mdmovie.bean.listBean.MoiveListBean;

import java.util.List;

/**
 * Created by Rannver on 2017/4/21.
 */

public interface HotContract {

    interface HotView extends BaseView<HotPresenter>{

        void initList();

        void SetHotList(List<MoiveListBean> list);

        void initText();

    }

    interface HotPresenter extends BasePresenter{
        void initView(HotView hotView);

        void  ModleOK();

        void  ModleFalse();
    }


}
