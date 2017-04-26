package com.kisen.mvpframe.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.kisen.mvpframe.R;
import com.kisen.mvplib.model.ModelException;
import com.kisen.mvplib.model.ModelResult;
import com.kisen.mvplib.model.ResultAnalysis;
import com.kisen.mvplib.view.MvpActivity;
import com.kisen.mvpframe.util.RequestParam;

/**
 *
 */
public class LoginActivity extends MvpActivity<LoginPresenter> implements View.OnClickListener {

    private TextView username;
    private TextView password;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void initView() {
        username = (TextView) findViewById(R.id.username);
        password = (TextView) findViewById(R.id.pwd);
        btnLogin = (Button) findViewById(R.id.btn_login);
    }

    @Override
    public void initData() {
    }

    public void initListener() {
        btnLogin.setOnClickListener(this);
    }

    @Override
    public LoginPresenter newPresenter() {
        return new LoginPresenter();
    }

    @Override
    public void onClick(View v) {
        if (checkError()) {
            return;
        }
        RequestParam param = new RequestParam();
        param.put("username", username.getText());
        param.put("password", password.getText());
        getPresenter().login(101, param);
    }

    @Override
    public void onModelComplete(ModelResult result) {
        result.analysis(new ResultAnalysis() {
            @Override
            public void success(int reqCode, Bundle args) {
                if (reqCode == 101) {
                    LoginData loginResult = getPresenter().getLoginResult();
                    Toast.makeText(mContext, "用户名：" + loginResult.getUserName() +
                            " 密码：" + loginResult.getPwd(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void fail(int reqCode, ModelException e) {
            }

            @Override
            public void finish(int reqCode) {
            }
        });
    }

    @Override
    protected void handleError(ModelException e) {

    }

    private boolean checkError() {
        if (TextUtils.isEmpty(username.getText())) {
            Toast.makeText(mContext, "用户名不能为空", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (TextUtils.isEmpty(password.getText())) {
            Toast.makeText(mContext, "密码不能为空", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
}
