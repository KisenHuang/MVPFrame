package com.kisen.mvplib.model;

/**
 * @Title :
 * @Description :
 * @Version :
 * Created by huang on 2017/3/22.
 */
public class ModelException extends Exception {

    public static final int ERROR_NET_SERVER = 1;//服务器返回错误
    public static final int ERROR_NET_NONE = 2;//网络异常
    public static final int ERROR_NET_UNSTABLE = 3;//网速慢，网络不稳定
    public static final int ERROR_IO_CACHE = 4;//缓存读取失败
    public static final int ERROR_IO_LOCAL = 5;//本地数据读取失败

    private int errorType;

    public ModelException(int errorType, String detailMessage) {
        super(detailMessage);
        this.errorType = errorType;
    }

    public ModelException(int errorType, String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
        this.errorType = errorType;
    }

    public ModelException(int errorType, Throwable throwable) {
        super(throwable);
        this.errorType = errorType;
    }

    public void setErrorType(int errorType) {
        this.errorType = errorType;
    }

    public int getErrorType() {
        return errorType;
    }
}
