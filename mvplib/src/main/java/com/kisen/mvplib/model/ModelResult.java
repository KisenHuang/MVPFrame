package com.kisen.mvplib.model;

import android.os.Bundle;

/**
 * Model返回结果
 * <p>
 * 不推荐直接使用方法：
 * {@link ModelResult#getBundle()}
 * {@link ModelResult#getException()}
 * {@link ModelResult#getResultCode()}
 * 推荐使用{@link ModelResult#analysis(ResultAnalysis)}方法
 * 将数据解析返回
 * </p>
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

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    @Deprecated
    public Bundle getBundle() {
        return bundle;
    }

    @Deprecated
    public int getResultCode() {
        return resultCode;
    }

    @Deprecated
    public ModelException getException() {
        return exception;
    }

    public ResultState getResultState() {
        return state;
    }

    /**
     * 结果解析方法
     * 将结果通过接口回调的方式进行解析，方便分类处理
     *
     * @param call 解析接口类
     */
    public void analysis(ResultAnalysis call) {
        switch (state) {
            case RESULT_OK:
                call.success(resultCode, bundle);
                break;
            case RESULT_ERROR:
                call.fail(resultCode, exception);
                break;
            default:
                call.finish(resultCode);
                break;
        }
    }

    public enum ResultState {
        RESULT_OK,
        RESULT_ERROR
    }
}
