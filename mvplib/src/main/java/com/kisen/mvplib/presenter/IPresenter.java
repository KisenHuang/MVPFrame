package com.kisen.mvplib.presenter;

import android.app.Activity;

import com.kisen.mvplib.view.IView;

/**
 * 页面数据处理逻辑
 * Created by huang on 2017/2/7.
 */

public interface IPresenter {

    /**
     * Presenter被初始化时调用
     *
     * @param view Activity对象
     */
    void attachView(IView view);

    /**
     * Activity被销毁时调用
     * {@link Activity#onDestroy()}
     */
    void detachView();
}
