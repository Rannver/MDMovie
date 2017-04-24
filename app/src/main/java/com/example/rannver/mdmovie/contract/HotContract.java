package com.example.rannver.mdmovie.contract;

import com.example.rannver.mdmovie.BasePresenter;
import com.example.rannver.mdmovie.BaseView;
import com.example.rannver.mdmovie.bean.listBean.HotListBean;

import java.util.List;

/**
 * Created by Rannver on 2017/4/21.
 */

public interface HotContract {

    interface HotView extends BaseView<HotPresenter>{

        void initList();

        void SetHotList(List<HotListBean> list);

    }

    interface HotPresenter extends BasePresenter{
        void initView(HotView hotView);

        void  ModleOK();
    }

    interface HotDetailView extends BaseView<HotDetailPresenter>{

        void initView();

        void initBackground();

        void initMovie();

        void initIntroduce();

        void initPerformer();
    }

    interface HotDetailPresenter extends BasePresenter{
    }

}
