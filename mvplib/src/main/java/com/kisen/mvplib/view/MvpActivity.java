package com.kisen.mvplib.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.kisen.mvplib.model.ModelException;
import com.kisen.mvplib.model.ModelResult;
import com.kisen.mvplib.model.ResultAnalysis;
import com.kisen.mvplib.presenter.IPresenter;

/**
 * Mvp模式View模板Activity基类
 * Created by huang on 2017/2/7.
 */
public abstract class MvpActivity<P extends IPresenter> extends AppCompatActivity implements View<P> {

    private P presenter;
    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        initView();
        initData();
        initListener();
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
    public void onModelComplete(ModelResult result) {
        result.analysis(new ResultAnalysis.DefResultAnalysis() {

            @Override
            public void fail(int reqCode, ModelException e) {
                handleError(e);
            }
        });
    }

    /**
     * Model错误处理
     *
     * @param e 异常
     */
    protected abstract void handleError(ModelException e);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mContext = null;
        if (presenter != null) {
            presenter.detachView();
            presenter = null;
        }
    }
}
