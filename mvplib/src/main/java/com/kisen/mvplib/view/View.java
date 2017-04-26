package com.kisen.mvplib.view;

import com.kisen.mvplib.model.ModelResult;
import com.kisen.mvplib.presenter.BasePresenter;

/**
 * Activity接口
 * Created by huang on 2017/2/7.
 */
public interface View<P extends BasePresenter> {

    /**
     * 初始化视图
     */
    void initView();

    /**
     * 初始化数据
     */
    void initData();

    /**
     * 初始化监听
     */
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
