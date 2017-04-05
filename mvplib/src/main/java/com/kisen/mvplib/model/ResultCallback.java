package com.kisen.mvplib.model;

/**
 * 结果回调
 * <p>
 * 用于返回Model获取数据的结果
 * <p>
 * Created by huang on 2017/3/23.
 */
public interface ResultCallback {
    /**
     * Model处理数据结束
     * 该方法包括处理成功和处理失败
     *
     * @param result 返回处理结果，包括本次的处理码，处理异常，处理结果
     */
    void onComplete(ModelResult result);
}
