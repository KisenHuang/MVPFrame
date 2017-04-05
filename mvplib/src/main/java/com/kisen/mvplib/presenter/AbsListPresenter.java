package com.kisen.mvplib.presenter;

import com.kisen.mvplib.bean.Data;
import com.kisen.mvplib.listhelper.AbsItem;
import com.kisen.mvplib.listhelper.BaseAdapter;
import com.kisen.mvplib.listhelper.ItemLogic;
import com.kisen.mvplib.listhelper.ItemFactory;
import com.kisen.mvplib.view.IView;

import java.util.List;


/**
 * 列表Presenter
 * Created by huang on 2017/2/7.
 */
public abstract class AbsListPresenter<D extends Data> extends AbsPresenter {

    private AbsItem<D> mItemTemplate;
    private ItemFactory<D> factory;
    private ItemLogic itemLogic;
    private BaseAdapter<AbsItem<D>> adapter;

    @Override
    public void attachView(IView view) {
        super.attachView(view);
        mItemTemplate = setupItemTemplate();
        adapter = new BaseAdapter<>();
        factory = new ItemFactory<>(view, mItemTemplate, adapter);
    }

    /**
     * 在父类中注册ItemLogic，
     * 主要是在创建Item时传给所有Item，保持所有Item都持有一个ItemLogic对象
     * {@link ItemFactory#makeItems(List, ItemLogic)}
     *
     * @param logic 在父类中注册的ItemLogic
     */
    protected void setItemLogic(ItemLogic logic) {
        itemLogic = logic;
    }

    @Override
    public void detachView() {
        super.detachView();
        if (itemLogic != null) {
            itemLogic.clear();
            itemLogic = null;
        }
        mItemTemplate = null;
        factory = null;
        adapter.clear();
    }

    /**
     * 通过list生产出Item列表
     * {@link ItemFactory#makeItems(List, ItemLogic)}
     *
     * @param list 生产Item所需数据源
     */
    public void notifyAfterLoad(List<D> list) {
        List<AbsItem<D>> items = factory.makeItems(list, itemLogic);
        adapter.addData(items);
    }

    public BaseAdapter<AbsItem<D>> getAdapter() {
        return adapter;
    }

    /**
     * 设置Item模板用于生产列表
     * {@link AbsItem#newSelf()}
     *
     * @return 一个Item模板
     */
    protected abstract AbsItem<D> setupItemTemplate();
}
