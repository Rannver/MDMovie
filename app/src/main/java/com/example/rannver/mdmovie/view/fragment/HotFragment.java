package com.example.rannver.mdmovie.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rannver.mdmovie.R;
import com.example.rannver.mdmovie.bean.listBean.HotListBean;
import com.example.rannver.mdmovie.contract.HotContract;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Rannver on 2017/4/20.
 */

public class HotFragment extends BaseFragment implements HotContract.HotView {

    @BindView(R.id.hotList)
    RecyclerView hotList;
    private View view;
    private HotContract.HotPresenter hotPresenter;
    private List<HotListBean> hotlist;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.hot_fragment, container, false);
        ButterKnife.bind(this, view);
        System.out.println("Fragment:this is hot");
        return view;
    }

    @Override
    public void setPresenter(HotContract.HotPresenter presenter) {
        hotPresenter = checkNotNull(presenter);
    }

    @Override
    public void initList() {

    }

    @Override
    public void SetHotList(List<HotListBean> list) {
        hotlist = list;
    }


}
