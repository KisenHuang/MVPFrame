package com.kisen.mvplib.model;

import android.os.Bundle;

/**
 * Model返回结果解析接口
 * Created by huang on 2017/4/26.
 */
public interface ResultAnalysis {
    /**
     * 请求成功
     *
     * @param reqCode 请求码
     * @param args    返回数据
     */
    void success(int reqCode, Bundle args);

    /**
     * 请求失败
     *
     * @param reqCode 请求码
     * @param e       异常
     */
    void fail(int reqCode, ModelException e);

    /**
     * 请求结束
     *
     * @param reqCode 请求码
     */
    void finish(int reqCode);

    public static class DefResultAnalysis implements ResultAnalysis {

        @Override
        public void success(int reqCode, Bundle args) {

        }

        @Override
        public void fail(int reqCode, ModelException e) {

        }

        @Override
        public void finish(int reqCode) {

        }
    }
}
