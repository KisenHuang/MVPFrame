package com.kisen.mvpframe.mvp.view;

import com.kisen.mvpframe.mvp.presenter.IPresenter;

/**
 *
 * Created by huang on 2017/2/7.
 */
public interface IView {

    /**
     * 加载前准备
     */
    void onPreLoad();

    /**
     * 加载数据
     *
     * @param presenter 数据持有类
     *                  @see com.kisen.mvpframe.mvp.presenter.ListPresenter
     *                  and
     *                  @see IPresenter
     */
    void onLoadData(IPresenter presenter);

}
