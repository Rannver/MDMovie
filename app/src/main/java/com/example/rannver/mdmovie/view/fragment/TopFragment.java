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
import com.example.rannver.mdmovie.contract.TopContract;
import com.example.rannver.mdmovie.view.activity.MoiveDetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Rannver on 2017/4/20.
 */

public class TopFragment extends BaseFragment implements TopContract.TopView {

    @BindView(R.id.moiveList)
    RecyclerView moiveList;
    @BindView(R.id.moiveText)
    TextView moiveText;

    private View view;
    private TopContract.TopPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.top_fragment, container, false);
        ButterKnife.bind(this, view);
        System.out.println("Fragment:this is top");
        return view;
    }

    @Override
    public void setPresenter(TopContract.TopPresenter presenter) {
        this.presenter = checkNotNull(presenter);
        this.presenter.initView(TopFragment.this);
    }

    @Override
    public void initList(List<MoiveListBean> list) {
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
    public void initText() {
        moiveList.setVisibility(View.GONE);
        moiveText.setText("无法连接到服务器，请重试_(:зゝ∠)_");
    }

}
