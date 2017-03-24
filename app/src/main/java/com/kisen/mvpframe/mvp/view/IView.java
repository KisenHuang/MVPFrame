package com.kisen.mvpframe.mvp.view;

import com.kisen.mvpframe.mvp.model.ModelResult;
import com.kisen.mvpframe.mvp.presenter.IPresenter;

/**
 * Activity接口
 * Created by huang on 2017/2/7.
 */
public interface IView<P extends IPresenter> {

    void initView();

    void initData();

    void initListener();

    /**
     * 创建Presenter
     *
     * @return IPresenter实现类
     */
    P newPresenter();

    /**
     * 打开加载动画
     */
    void openLoadingAnim();

    /**
     * 关闭加载动画
     */
    void closeLoadingAnim();

    void onModelComplete(ModelResult result);
}
