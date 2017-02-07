package com.kisen.mvpframe.mvp.presenter;

import com.kisen.mvpframe.mvp.bean.IData;

/**
 * @Title :
 * @Description :
 * @Version :
 * Created by huang on 2017/2/7.
 */

public abstract class AbsPresenter implements IPresenter {

    private IData data;

    public void setData(IData data) {
        this.data = data;
    }

    /**
     * 返回持有数据
     *
     * @return
     */
    public IData getData() {
        return data;
    }
}
