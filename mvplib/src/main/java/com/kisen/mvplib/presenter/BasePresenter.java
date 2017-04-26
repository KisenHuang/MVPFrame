package com.kisen.mvplib.presenter;

import com.kisen.mvplib.model.ModelResult;
import com.kisen.mvplib.model.ResultCallback;
import com.kisen.mvplib.view.View;

/**
 * 普通页面使用的Presenter
 * Created by huang on 2017/2/7.
 */
public class BasePresenter implements IPresenter {

    private View view;

    protected View getView() {
        return view;
    }

    @Override
    public void attachView(View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    /**
     * 默认实现结果回调类
     */
    public class DefResultCallback implements ResultCallback {

        @Override
        public void onComplete(ModelResult result) {
            //关闭加载动画
            getView().closeLoadingAnim();
            //通知View，数据加载完毕
            getView().onModelComplete(result);
        }
    }
}
