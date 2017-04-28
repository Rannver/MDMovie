package com.example.rannver.mdmovie.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.rannver.mdmovie.R;
import com.example.rannver.mdmovie.adpter.CastsAdpter;
import com.example.rannver.mdmovie.bean.listBean.CastListBean;
import com.example.rannver.mdmovie.bean.listBean.MoiveDetailBean;
import com.example.rannver.mdmovie.contract.MoiveDetailContract;
import com.example.rannver.mdmovie.presenter.MoiveDetailPresenter;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rannver on 2017/4/26.
 */

public class MoiveDetailActivity extends AppCompatActivity implements MoiveDetailContract.DetailView {


    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.moiveText)
    TextView moiveText;
    @BindView(R.id.img_head)
    ImageView imgHead;
    @BindView(R.id.detail_titile)
    TextView detailTitile;
    @BindView(R.id.detai_genres)
    TextView detaiGenres;
    @BindView(R.id.detail_year)
    TextView detailYear;
    @BindView(R.id.detail_country)
    TextView detailCountry;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    @BindView(R.id.avgRate)
    TextView avgRate;
    @BindView(R.id.rateBar)
    RatingBar rateBar;
    @BindView(R.id.rateCount)
    TextView rateCount;
    @BindView(R.id.rate_card)
    LinearLayout rateCard;
    @BindView(R.id.textIntroduce)
    TextView textIntroduce;
    @BindView(R.id.directorList)
    RecyclerView directorList;
    @BindView(R.id.castList)
    RecyclerView castList;
    @BindView(R.id.detailview)
    NestedScrollView detailview;

    private String TAG = "MoiveDetailActivity";

    private MoiveDetailContract.DetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moivedetail_activity);
        ButterKnife.bind(this);
        setPresenter(new MoiveDetailPresenter());
        initView();
    }

    @Override
    public void setPresenter(MoiveDetailContract.DetailPresenter presenter) {
        this.presenter = presenter;
        this.presenter.initView(MoiveDetailActivity.this);
    }

    @Override
    public void initView() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("");
        }

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        System.out.println(TAG+":"+id);
        presenter.getMoiveDetail(Integer.parseInt(id));

    }

    @Override
    public void initDetail(MoiveDetailBean detailBean) {

        Picasso.with(MoiveDetailActivity.this)
                .load(detailBean.getUrl())
                .placeholder(R.drawable.ic_movie)
                .error(R.drawable.ic_movie)
                .resize(500,680)
                .into(imgHead);

        toolbarTitle.setText(detailBean.getTitle());
        detailTitile.setText(detailBean.getTitle());
        detaiGenres.setText("类别："+detailBean.getGenres());
        detailYear.setText("年份："+detailBean.getYear());
        detailCountry.setText("制片地区："+detailBean.getCountries());
        textIntroduce.setText("    "+detailBean.getIntroduce());
        avgRate.setText(""+detailBean.getAvgRate());
        rateCount.setText(""+detailBean.getCount()+"人");
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void initRating(int rate) {
        rateBar.setRating(rate);
    }

    @Override
    public void initList(List<CastListBean> directors, List<CastListBean> casts) {
        moiveText.setVisibility(View.GONE);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(MoiveDetailActivity.this);
        linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(MoiveDetailActivity.this);
        linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        castList.setLayoutManager(linearLayoutManager1);
        directorList.setLayoutManager(linearLayoutManager2);

        CastsAdpter castsAdpter = new CastsAdpter(casts,MoiveDetailActivity.this);
        CastsAdpter directorsAdpter = new CastsAdpter(directors,MoiveDetailActivity.this);
        directorList.setAdapter(directorsAdpter);
        castList.setAdapter(castsAdpter);

    }

    @Override
    public void initText() {
        detailview.setVisibility(View.GONE);
        moiveText.setText("无法连接到服务器，请重试_(:зゝ∠)_");
    }
}
