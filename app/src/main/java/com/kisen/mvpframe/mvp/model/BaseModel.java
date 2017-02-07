package com.kisen.mvpframe.mvp.model;

import android.content.Context;

import com.kisen.mvpframe.mvp.bean.IData;

import java.util.List;

/**
 * @Title :
 * @Description : 需要提供网络加载工具方法
 * @Version :
 * Created by huang on 2017/2/7.
 */
public abstract class BaseModel implements IModel {

    private Context context;

    public BaseModel(Context context) {
        this.context = context;
    }

    /**
     * @see OnLoadListener 的简单实现
     * 使用时只需要重写自己需要的方法
     */
    public static class OnLoadListenerImpl implements OnLoadListener {

        @Override
        public void onComplete(List<IData> list) {

        }

        @Override
        public void onComplete(IData data) {

        }
    }
}
