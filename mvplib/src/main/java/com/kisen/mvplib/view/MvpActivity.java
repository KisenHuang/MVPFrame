package com.kisen.mvplib.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.kisen.mvplib.model.ModelResult;
import com.kisen.mvplib.presenter.IPresenter;
import com.kisen.mvplib.model.ModelException;

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

    private void dismissProgress() {
        if (mProgressDialog.isShowing())
            mProgressDialog.dismiss();
    }

    /**
     * 初始化Presenter并关联Activity
     *
     * @return presenter
     */
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
    public boolean onModelComplete(ModelResult result) {
        if (result.getResultState() == ModelResult.ResultState.RESULT_ERROR) {
            if (result.getException() != null)
                handleError(result.getException());
            return false;
        } else {
            return true;
        }
    }

    /**
     * 错误处理
     *
     * @param e 异常
     */
    protected abstract void handleError(ModelException e);

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

    @Override
    public void openLoadingAnim() {
        showProgress();
    }

    @Override
    public void closeLoadingAnim() {
        dismissProgress();
    }
}
