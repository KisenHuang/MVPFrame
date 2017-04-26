package com.kisen.mvpframe.login;

import com.kisen.mvplib.model.ModelException;
import com.kisen.mvplib.model.ModelResult;
import com.kisen.mvplib.model.ResultCallback;
import com.kisen.mvpframe.util.RequestParam;

/**
 * @Title :
 * @Description :
 * @Version :
 * Created by huang on 2017/3/23.
 */

public class LoginModel {

    public void login(int resultCode, RequestParam param, ResultCallback callback) {
        if (param == null) {
            ModelException exception = new ModelException(ModelException.ERROR_NET_NONE, "网络异常");
            callback.onComplete(new ModelResult(resultCode, exception));
        } else {
            callback.onComplete(new ModelResult(resultCode));
        }
    }

    public LoginData getResult() {
        return new LoginData("qwe", "123");
    }

}
