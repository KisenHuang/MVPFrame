package com.kisen.mvpframe;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kisen.mvpframe.mvp.adapter.BaseAdapter;
import com.kisen.mvpframe.mvp.presenter.IPresenter;
import com.kisen.mvpframe.mvp.presenter.ListPresenter;
import com.kisen.mvpframe.mvp.view.IView;

public class MainActivity extends AppCompatActivity implements IView {

    private RecyclerView mRecyclerView;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressDialog = new ProgressDialog(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        new MainPresenter(this).fetch();
    }

    @Override
    public void onPreLoad() {
        showProgress();
    }

    @Override
    public void onLoadData(IPresenter presenter) {
        dismissProgress();
        mRecyclerView.setAdapter(new BaseAdapter((ListPresenter)presenter));
    }

    private void showProgress() {
        mProgressDialog.setTitle("正在加载数据...");
        mProgressDialog.show();
    }

    private void dismissProgress() {
        if (mProgressDialog.isShowing())
            mProgressDialog.dismiss();
    }
}
