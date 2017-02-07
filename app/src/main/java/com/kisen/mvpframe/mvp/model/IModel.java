package com.kisen.mvpframe.mvp.model;

import com.kisen.mvpframe.mvp.bean.IData;

import java.util.List;

/**
 * @Title :
 * @Description :
 * @Version :
 * Created by huang on 2017/2/7.
 */
public interface IModel {

    /**
     * 加载数据
     *
     * @param listener 加载监听
     */
    void load(OnLoadListener listener);

    /**
     * 加载监听
     */
    interface OnLoadListener {
        /**
         * 列表加载完成回调
         */
        void onComplete(List<IData> list);

        /**
         * 简单界面
         */
        void onComplete(IData data);
    }

}
