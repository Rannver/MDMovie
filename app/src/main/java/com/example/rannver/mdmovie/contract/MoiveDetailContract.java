package com.example.rannver.mdmovie.contract;

import com.example.rannver.mdmovie.BasePresenter;
import com.example.rannver.mdmovie.BaseView;
import com.example.rannver.mdmovie.bean.listBean.CastListBean;
import com.example.rannver.mdmovie.bean.listBean.MoiveDetailBean;

import java.util.List;

/**
 * Created by Rannver on 2017/4/28.
 */

public interface MoiveDetailContract {

    public interface DetailView extends BaseView<DetailPresenter>{

        void initView();

        void initDetail(MoiveDetailBean detailBean);

        void initRating(int rate);


        void initList(List<CastListBean> directors,List<CastListBean> casts);

        void initText();
    }

    public interface DetailPresenter extends BasePresenter{

        void initView(DetailView detailView);

        void getMoiveDetail(int id);

        void ModleOK();

        void ModleFalse();
    }
}
