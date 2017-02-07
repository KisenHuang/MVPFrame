package com.kisen.mvpframe.mvp.presenter;

import com.kisen.mvpframe.mvp.bean.IData;
import com.kisen.mvpframe.mvp.model.BaseModel;
import com.kisen.mvpframe.mvp.model.IModel;
import com.kisen.mvpframe.mvp.view.IView;

/**
 * @Title :
 * @Description :
 * @Version :
 * Created by huang on 2017/2/7.
 */

public abstract class AbsPresenter implements IPresenter {

    private IData data;

    protected IView view;

    protected IModel model;

    public AbsPresenter(IView view) {
        this.view = view;
        model = setupModel(view);
    }

    protected abstract IModel setupModel(IView view);

    @Override
    public void fetch() {
        view.onPreLoad();
        if (view.isReadyToLoad())
            model.load(new BaseModel.OnLoadListenerImpl() {
                @Override
                public void onComplete(IData data) {
                    setData(data);
                    view.onLoadData(AbsPresenter.this);
                }
            });
    }

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
