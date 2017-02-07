package com.kisen.mvpframe.login;

import com.kisen.mvpframe.mvp.bean.IData;

/**
 * @Title :
 * @Description :
 * @Version :
 * Created by huang on 2017/2/7.
 */

public class LoginData implements IData {
    private String userName;
    private String pwd;

    public LoginData(String userName, String pwd) {
        this.userName = userName;
        this.pwd = pwd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
