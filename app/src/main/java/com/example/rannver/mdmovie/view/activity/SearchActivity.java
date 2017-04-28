package com.example.rannver.mdmovie.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rannver.mdmovie.R;
import com.example.rannver.mdmovie.adpter.MoiveListAdpter;
import com.example.rannver.mdmovie.bean.listBean.MoiveListBean;
import com.example.rannver.mdmovie.contract.SearchContract;
import com.example.rannver.mdmovie.presenter.SearchPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Rannver on 2017/4/28.
 */

public class SearchActivity extends AppCompatActivity implements SearchContract.SearchView {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.moiveList)
    RecyclerView moiveList;
    @BindView(R.id.moiveText)
    TextView moiveText;

    private String TAG = "SearchActivity";

    private SearchContract.SearchPresenter presenter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);
        ButterKnife.bind(this);
        setPresenter(new SearchPresenter());
        Log.d(TAG,"this is SearchActivity");
    }

    @Override
    public void setPresenter(SearchContract.SearchPresenter presenter) {
        this.presenter = checkNotNull(presenter);
        this.presenter.initView(SearchActivity.this);
    }

    @Override
    public void initView() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home :
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_searchview, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView = (SearchView) item.getActionView();
        searchView.setMaxWidth(6000);
        searchView.setIconifiedByDefault(false);
        final ImageView imageView = (ImageView) searchView.findViewById(R.id.search_mag_icon);
        imageView.setVisibility(View.GONE);
        TextChangeListener();
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void TextChangeListener() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                System.out.println(TAG+":"+query);
                presenter.GetListInfo(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //TextChange
                return false;
            }
        });
    }

    @Override
    public void initList(List<MoiveListBean> list) {
        moiveText.setVisibility(View.GONE);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SearchActivity.this);
        moiveList.setLayoutManager(linearLayoutManager);
        MoiveListAdpter adpter = new MoiveListAdpter(list, SearchActivity.this,MoiveListAdpter.VIEW_HOT);
        moiveList.setAdapter(adpter);
        adpter.SetOnItemClickListener(new MoiveListAdpter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position, String id) {
                Intent intent = new Intent(SearchActivity.this, MoiveDetailActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
    }

    @Override
    public void initText(String s) {
        moiveList.setVisibility(View.GONE);
        if (s!=null){
            moiveText.setText("无法连接到服务器，请重试_(:зゝ∠)_");
        }else {
            moiveText.setText(s);
        }
    }
}
