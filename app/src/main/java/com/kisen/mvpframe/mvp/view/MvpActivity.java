package com.kisen.mvpframe.mvp.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.kisen.mvpframe.mvp.model.ModelResult;
import com.kisen.mvpframe.mvp.presenter.IPresenter;
import com.kisen.mvpframe.mvp.model.ModelException;

/**
 * @Title :
 * @Description :
 * @Version :
 * Created by huang on 2017/2/7.
 */
public abstract class MvpActivity<P extends IPresenter> extends AppCompatActivity implements IView<P> {

    private ProgressDialog mProgressDialog;
    private P presenter;
    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mProgressDialog = new ProgressDialog(this);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        initView();
        initData();
        initListener();
    }

    private void showProgress() {
        if (mProgressDialog.isShowing())
            return;
        mProgressDialog.setMessage("正在加载数据...");
        mProgressDialog.show();
    }

    protected P getPresenter() {
        if (presenter == null) {
            presenter = newPresenter();
            if (presenter != null) {
                presenter.attachView(this);
            }
        }
        return presenter;
    }

    @Override
    public void onModelComplete(ModelResult result) {
        if (result.getResultState() == ModelResult.ResultState.RESULT_ERROR) {
            if (result.getException() == null)
                return;
            switch (result.getException().getErrorType()) {
                case ModelException.ERROR_NET_NONE:
                    break;
                case ModelException.ERROR_NET_UNSTABLE:
                    break;
                case ModelException.ERROR_NET_SERVER:
                    break;
                case ModelException.ERROR_IO_CACHE:
                    break;
                case ModelException.ERROR_IO_LOCAL:
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
            presenter = null;
        }
        dismissProgress();
        mProgressDialog = null;
    }

    private void dismissProgress() {
        if (mProgressDialog.isShowing())
            mProgressDialog.dismiss();
    }

    @Override
    public void openLoadingAnim() {
        showProgress();
    }

    @Override
    public void closeLoadingAnim() {
        dismissProgress();
    }
}
