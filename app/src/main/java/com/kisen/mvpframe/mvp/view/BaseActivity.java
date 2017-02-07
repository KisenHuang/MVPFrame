package com.kisen.mvpframe.mvp.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * @Title :
 * @Description :
 * @Version :
 * Created by huang on 2017/2/7.
 */

public abstract class BaseActivity extends AppCompatActivity implements IView{

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProgressDialog = new ProgressDialog(this);
    }

    public void showProgress() {
        mProgressDialog.setTitle("正在加载数据...");
        mProgressDialog.show();
    }

    public void dismissProgress() {
        if (mProgressDialog.isShowing())
            mProgressDialog.dismiss();
    }
}
