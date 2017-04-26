package com.kisen.mvplib.view;

import com.kisen.mvplib.model.ModelResult;
import com.kisen.mvplib.presenter.IPresenter;

/**
 * Activity接口
 * Created by huang on 2017/2/7.
 */
public interface View<P extends IPresenter> {

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

    /**
     * Model加载数据完成回调方法
     *
     * @param result 返回结果
     */
    void onModelComplete(ModelResult result);
}
