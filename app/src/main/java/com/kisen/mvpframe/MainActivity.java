package com.kisen.mvpframe;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kisen.mvpframe.mvp.adapter.BaseAdapter;
import com.kisen.mvpframe.mvp.presenter.IPresenter;
import com.kisen.mvpframe.mvp.presenter.ListPresenter;
import com.kisen.mvpframe.mvp.view.BaseActivity;

public class MainActivity extends BaseActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        new MainPresenter(this).fetch();
    }

    @Override
    public void onPreLoad() {
        showProgress();
    }

    @Override
    public boolean isReadyToLoad() {
        return true;
    }

    @Override
    public void onLoadData(IPresenter presenter) {
        dismissProgress();
        mRecyclerView.setAdapter(new BaseAdapter((ListPresenter)presenter));
    }

}
