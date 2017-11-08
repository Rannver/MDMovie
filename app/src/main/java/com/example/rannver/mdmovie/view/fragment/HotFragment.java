package com.example.rannver.mdmovie.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rannver.mdmovie.R;
import com.example.rannver.mdmovie.adpter.MoiveListAdpter;
import com.example.rannver.mdmovie.bean.listBean.MoiveListBean;
import com.example.rannver.mdmovie.contract.HotContract;
import com.example.rannver.mdmovie.view.activity.MoiveDetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;


/**
 * Created by Rannver on 2017/4/20.
 */

public class HotFragment extends BaseFragment implements HotContract.HotView {


    @BindView(R.id.moiveList)
    RecyclerView moiveList;
    @BindView(R.id.moiveText)
    TextView moiveText;

    private String TAG = "HotFragment";

    private View view;
    private HotContract.HotPresenter hotPresenter;
    private List<MoiveListBean> list;

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
        hotPresenter.initView(HotFragment.this);
    }

    @Override
    public void initList() {
        moiveText.setVisibility(View.GONE);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        moiveList.setLayoutManager(linearLayoutManager);
        MoiveListAdpter adpter = new MoiveListAdpter(list, getContext(),MoiveListAdpter.VIEW_HOT);
        moiveList.setAdapter(adpter);

        adpter.SetOnItemClickListener(new MoiveListAdpter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position, String id) {
                Intent intent = new Intent(getActivity(), MoiveDetailActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
    }

    @Override
    public void SetHotList(List<MoiveListBean> list) {
        this.list = list;
    }

    @Override
    public void initText() {
        moiveList.setVisibility(View.GONE);
        moiveText.setText("无法连接到服务器，请重试_(:зゝ∠)_");
    }

}
