package com.kisen.mvpframe.login;

import android.os.Bundle;

import com.kisen.mvpframe.R;
import com.kisen.mvpframe.mvp.presenter.IPresenter;
import com.kisen.mvpframe.mvp.view.BaseActivity;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        new LoginPresenter(this).fetch();
    }

    @Override
    public void onPreLoad() {
        //// TODO: 2017/2/7 ?
    }

    private boolean isLogin(){
        return true;
    }

    @Override
    public boolean isReadyToLoad() {
        return isLogin();
    }

    @Override
    public void onLoadData(IPresenter presenter) {
        // TODO: 2017/2/7 ?
    }
}
