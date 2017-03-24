package com.kisen.mvpframe.login;

import com.kisen.mvpframe.mvp.model.ModelResult;
import com.kisen.mvpframe.mvp.model.ResultCallback;
import com.kisen.mvpframe.mvp.presenter.AbsPresenter;
import com.kisen.mvpframe.mvp.util.RequestParam;
import com.kisen.mvpframe.mvp.view.IView;

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
