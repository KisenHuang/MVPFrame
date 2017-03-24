package com.kisen.mvpframe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.kisen.mvpframe.list.ListActivity;
import com.kisen.mvpframe.list.ListData;
import com.kisen.mvpframe.login.LoginActivity;
import com.kisen.mvpframe.mvp.presenter.AbsListPresenter;
import com.kisen.mvpframe.mvp.view.MvpActivity;

public class MainActivity extends MvpActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
    }

    @Override
    public void initListener() {
        findViewById(R.id.btn_list).setOnClickListener(this);
        findViewById(R.id.btn_login).setOnClickListener(this);

    }

    @Override
    public AbsListPresenter<ListData> newPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_list:
                startActivity(new Intent(mContext, ListActivity.class));
                break;
            case R.id.btn_login:
                startActivity(new Intent(mContext, LoginActivity.class));
                break;
        }
    }
}
