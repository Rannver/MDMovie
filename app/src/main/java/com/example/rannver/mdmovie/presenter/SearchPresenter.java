package com.example.rannver.mdmovie.presenter;

import com.example.rannver.mdmovie.bean.listBean.MoiveListBean;
import com.example.rannver.mdmovie.contract.SearchContract;
import com.example.rannver.mdmovie.model.SearchModel;

import java.util.List;

/**
 * Created by Rannver on 2017/4/28.
 */

public class SearchPresenter implements SearchContract.SearchPresenter{

    private String TAG = "SearchPresenter";

    private SearchModel searchModel;
    private SearchContract.SearchView searchView;
    private List<MoiveListBean> list;

    public SearchPresenter(){
        searchModel = new SearchModel(SearchPresenter.this);
    }

    @Override
    public void start() {

    }

    @Override
    public void initView(SearchContract.SearchView searchView) {
        this.searchView = searchView;
        searchView.initView();
    }

    @Override
    public void GetListInfo(String q) {
        searchModel.SetQuery(q);
    }

    @Override
    public void ModleOK() {
        list = searchModel.getList();
        searchView.initList(list);
    }

    @Override
    public void ModleFalse() {
        searchView.initText(null);
    }

    @Override
    public void ModleNull() {
        searchView.initText("搜索结果为空");
    }
}
