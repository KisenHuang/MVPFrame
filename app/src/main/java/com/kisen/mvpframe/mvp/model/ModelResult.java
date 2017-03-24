package com.kisen.mvpframe.mvp.model;

import android.os.Bundle;

/**
 * @Title :
 * @Description :
 * @Version :
 * Created by huang on 2017/3/23.
 */

public class ModelResult {

    private final ModelException exception;
    private final ResultState state;
    private int resultCode;
    private Bundle bundle;

    /**
     * 表示操作成功
     *
     * @param resultCode 操作状态码
     */
    public ModelResult(int resultCode) {
        this.resultCode = resultCode;
        state = ResultState.RESULT_OK;
        exception = null;
    }

    /**
     * 表示操作失败
     *
     * @param resultCode 操作状态码
     * @param e          异常
     */
    public ModelResult(int resultCode, ModelException e) {
        this.resultCode = resultCode;
        state = ResultState.RESULT_ERROR;
        exception = e;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    public int getResultCode() {
        return resultCode;
    }

    public ModelException getException() {
        return exception;
    }

    public ResultState getResultState() {
        return state;
    }

    public enum ResultState {
        RESULT_OK,
        RESULT_ERROR
    }
}
