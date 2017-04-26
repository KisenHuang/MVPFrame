package com.kisen.mvpframe.login;

import com.kisen.mvplib.model.ModelResult;
import com.kisen.mvplib.model.ResultCallback;
import com.kisen.mvplib.presenter.BasePresenter;
import com.kisen.mvplib.util.RequestParam;
import com.kisen.mvplib.view.View;

/**
 * 登录Presenter
 * <p>
 * 提供login函数：
 * 通过{@link LoginModel#login(int, RequestParam, ResultCallback)}进行网络通信，
 * 并将结果通过回调{@link ResultCallback#onComplete(ModelResult)}返回，
 * 再将处理结果通过{@link View#onModelComplete(ModelResult)}回馈给Activity
 * </p>
 * Created by huang on 2017/2/7.
 */

public class LoginPresenter extends BasePresenter {

    private LoginModel model;

    @Override
    public void attachView(View view) {
        super.attachView(view);
        model = new LoginModel();
    }

    public void login(int resultCode, RequestParam param) {
        getView().openLoadingAnim();
        model.login(resultCode, param, new ResultCallback() {

            @Override
            public void onComplete(ModelResult result) {
                getView().closeLoadingAnim();
                getView().onModelComplete(result);
            }
        });
    }

    public LoginData getLoginResult() {
        return model.getResult();
    }
}
