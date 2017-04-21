package com.example.rannver.mdmovie.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rannver.mdmovie.R;

import butterknife.ButterKnife;

/**
 * Created by Rannver on 2017/4/20.
 */

public class InfluentFragment extends BaseFragment {
    
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.influent_fragment, container, false);
        ButterKnife.bind(this, view);
        System.out.println("Fragment:this is influent");
        return view;
    }
}
