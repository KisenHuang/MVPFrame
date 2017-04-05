package com.kisen.mvpframe.login;

import com.kisen.mvplib.model.ModelResult;
import com.kisen.mvplib.model.ResultCallback;
import com.kisen.mvplib.presenter.AbsPresenter;
import com.kisen.mvplib.util.RequestParam;
import com.kisen.mvplib.view.IView;

/**
 * @Title :
 * @Description :
 * @Version :
 * Created by huang on 2017/2/7.
 */

public class LoginPresenter extends AbsPresenter {

    private LoginModel model;

    @Override
    public void attachView(IView view) {
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
