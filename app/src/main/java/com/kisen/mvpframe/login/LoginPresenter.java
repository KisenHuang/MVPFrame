package com.kisen.mvpframe.login;

import com.kisen.mvpframe.mvp.model.IModel;
import com.kisen.mvpframe.mvp.presenter.AbsPresenter;
import com.kisen.mvpframe.mvp.view.IView;

/**
 * @Title :
 * @Description :
 * @Version :
 * Created by huang on 2017/2/7.
 */

public class LoginPresenter extends AbsPresenter {

    public LoginPresenter(IView view) {
        super(view);
    }

    @Override
    protected IModel setupModel(IView view) {
        return new LoginModel();
    }
}
