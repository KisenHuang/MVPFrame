package com.kisen.mvpframe.mvp.presenter;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;

import com.kisen.mvpframe.MainPresenter;
import com.kisen.mvpframe.mvp.adapter.BaseViewHolder;
import com.kisen.mvpframe.mvp.bean.IData;
import com.kisen.mvpframe.mvp.model.BaseModel;
import com.kisen.mvpframe.mvp.model.IModel;
import com.kisen.mvpframe.mvp.view.IView;

import java.util.ArrayList;
import java.util.List;

/**
 * 列表Presenter
 * Created by huang on 2017/2/7.
 */

public abstract class ListPresenter implements IPresenter {

    private List<IData> list;

    protected IView view;

    protected IModel model;

    public ListPresenter(IView view){
        this.view = view;
        model = setupModel(view);
    }

    @Override
    public void fetch() {
        view.onPreLoad();
        if (view.isReadyToLoad())
            model.load(new BaseModel.OnLoadListenerImpl() {
                @Override
                public void onComplete(List<IData> list) {
                    setList(list);
                    view.onLoadData(ListPresenter.this);
                }
            });
    }

    protected abstract IModel setupModel(IView view);

    /**
     * 得到数据
     */
    @NonNull
    public List<IData> getList() {
        if (list == null)
            list = new ArrayList<>();
        return list;
    }

    public void setList(List<IData> list) {
        this.list = list;
    }

    /**
     * 返回itemUI样式
     */
    public abstract
    @LayoutRes
    int getItemResId();

    /**
     * @param holder   ViewHolder
     * @param position 位置
     * @see com.kisen.mvpframe.mvp.adapter.BaseAdapter 中的onBindViewHolder方法实现适配器绑定数据
     */
    public abstract void onBindViewHolder(BaseViewHolder holder, int position);

}
