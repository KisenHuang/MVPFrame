package com.kisen.mvpframe.login;

import android.os.Parcel;

import com.kisen.mvpframe.mvp.bean.Data;

/**
 * @Title :
 * @Description :
 * @Version :
 * Created by huang on 2017/2/7.
 */

public class LoginData extends Data {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userName);
        dest.writeString(this.pwd);
    }

    protected LoginData(Parcel in) {
        this.userName = in.readString();
        this.pwd = in.readString();
    }

    public static final Creator<LoginData> CREATOR = new Creator<LoginData>() {
        @Override
        public LoginData createFromParcel(Parcel source) {
            return new LoginData(source);
        }

        @Override
        public LoginData[] newArray(int size) {
            return new LoginData[size];
        }
    };
}
