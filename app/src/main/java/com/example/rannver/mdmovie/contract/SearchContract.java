package com.example.rannver.mdmovie.contract;

import com.example.rannver.mdmovie.BasePresenter;
import com.example.rannver.mdmovie.BaseView;
import com.example.rannver.mdmovie.bean.listBean.MoiveListBean;

import java.util.List;

/**
 * Created by Rannver on 2017/4/28.
 */

public interface SearchContract {

    public interface SearchView extends BaseView<SearchPresenter>{

        void initView();

        void TextChangeListener();

        void initList(List<MoiveListBean> list);

        void initText(String s);

    }
    public interface SearchPresenter extends BasePresenter{

        void initView(SearchView searchView);

        void GetListInfo(String q);

        void ModleOK();

        void ModleFalse();

        void ModleNull();
    }

}
