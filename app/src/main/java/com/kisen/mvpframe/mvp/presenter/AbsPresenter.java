package com.kisen.mvpframe.mvp.presenter;

import com.kisen.mvpframe.mvp.model.ModelResult;
import com.kisen.mvpframe.mvp.model.ResultCallback;
import com.kisen.mvpframe.mvp.view.IView;

/**
 * 普通页面使用的Presenter
 * Created by huang on 2017/2/7.
 */
public abstract class AbsPresenter implements IPresenter {

    private IView view;

    protected IView getView() {
        return view;
    }

    @Override
    public void attachView(IView view) {
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
