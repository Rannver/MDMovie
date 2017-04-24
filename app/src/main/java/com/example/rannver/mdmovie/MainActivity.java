package com.example.rannver.mdmovie;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.rannver.mdmovie.presenter.HotPresenter;
import com.example.rannver.mdmovie.view.fragment.FutureFragment;
import com.example.rannver.mdmovie.view.fragment.HotFragment;
import com.example.rannver.mdmovie.view.fragment.InfluentFragment;
import com.example.rannver.mdmovie.view.fragment.NewFragment;
import com.example.rannver.mdmovie.view.fragment.TicketFragment;
import com.example.rannver.mdmovie.view.fragment.TopFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity{


    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_main)
    Toolbar toolbarMain;
    @BindView(R.id.nav_type)
    NavigationView navType;
    @BindView(R.id.drawerLatout_main)
    DrawerLayout drawerLatoutMain;

    private HotFragment hotFragment;
    private FutureFragment futureFragment;
    private InfluentFragment influentFragment;
    private NewFragment newFragment;
    private TicketFragment ticketFragment;
    private TopFragment topFragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();

        navType.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                changeFragment(item.getTitle().toString());
                drawerLatoutMain.closeDrawers();
                return true;
            }
        });

    }

    //设置主界面内容
    private void initView() {
        setSupportActionBar(toolbarMain);
        toolbarTitle.setText("");
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        navType.setCheckedItem(R.id.nav_hot);

        fragmentManager = getSupportFragmentManager();
        hotFragment = new HotFragment();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.framLayout_main, hotFragment);
        transaction.commit();
        hotFragment.setPresenter(new HotPresenter());

    }

    private void changeFragment(String title) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        HideFragment(transaction);
        switch (title){
            case "正在热映":
                if (hotFragment==null){
                    hotFragment = new HotFragment();
                    transaction.add(R.id.framLayout_main,hotFragment);
                    hotFragment.setPresenter(new HotPresenter());
                    System.out.println("Fragment:new Hot Fragment");
                }else {
                    transaction.show(hotFragment);
                    System.out.println("Fragment:show Hot Fragment");
                }
                break;
            case "即将上映":
                if (futureFragment==null){
                    futureFragment = new FutureFragment();
                    transaction.add(R.id.framLayout_main,futureFragment);
                    System.out.println("Fragment:new Future Fragment");
                }else {
                    transaction.show(futureFragment);
                    System.out.println("Fragment:show Future Fragment");
                }
                break;
            case "TOP10":
                if (topFragment==null){
                    topFragment = new TopFragment();
                    transaction.add(R.id.framLayout_main,topFragment);
                    System.out.println("Fragment:new Top Fragment");
                }else {
                    transaction.show(topFragment);
                    System.out.println("Fragment:show Top Fragment");
                }
                break;
            case "口碑榜":
                if (influentFragment==null){
                    influentFragment = new InfluentFragment();
                    transaction.add(R.id.framLayout_main,influentFragment);
                    System.out.println("Fragment:new Influent Fragment");
                }else {
                    transaction.show(influentFragment);
                    System.out.println("Fragment:show Influent Fragment");
                }
                break;
            case "北美票房榜":
                if (ticketFragment==null){
                    ticketFragment = new TicketFragment();
                    transaction.add(R.id.framLayout_main,ticketFragment);
                    System.out.println("Fragment:new Ticket Fragment");
                }else {
                    transaction.show(ticketFragment);
                    System.out.println("Fragment:show Ticiket Fragment");
                }
                break;
            case "新片榜":
                if (newFragment==null){
                    newFragment = new NewFragment();
                    transaction.add(R.id.framLayout_main,newFragment);
                    System.out.println("Fragment:new New Fragment");
                }else {
                    transaction.show(newFragment);
                    System.out.println("Fragment:show New Fragment");
                }
                break;
            default:
                System.out.println("default");
                break;
        }
        transaction.commit();
    }

    //隐藏之前的fragment
    private void HideFragment(FragmentTransaction transaction) {
        if (hotFragment!=null){
            transaction.hide(hotFragment);
        }
        if (futureFragment!=null){
            transaction.hide(futureFragment);
        }
        if (topFragment!=null){
            transaction.hide(topFragment);
        }
        if (influentFragment!=null){
            transaction.hide(influentFragment);
        }
        if (ticketFragment!=null){
            transaction.hide(ticketFragment);
        }
        if (newFragment!=null){
            transaction.hide(newFragment);
        }

    }

    //toolbar的监听事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLatoutMain.openDrawer(GravityCompat.START);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
