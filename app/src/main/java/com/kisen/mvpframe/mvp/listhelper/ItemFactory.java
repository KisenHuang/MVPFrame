package com.kisen.mvpframe.mvp.listhelper;

import android.content.Context;

import com.kisen.mvpframe.mvp.bean.Data;
import com.kisen.mvpframe.mvp.view.IView;

import java.util.ArrayList;
import java.util.List;

/**
 * Item 工厂类
 * Created by huang on 2017/3/3.
 */

public class ItemFactory<D extends Data> {

    private AbsItem<D> template;
    private BaseAdapter<AbsItem<D>> adapter;
    private Context context;

    public ItemFactory(IView view, AbsItem<D> template, BaseAdapter<AbsItem<D>> adapter) {
        this.template = template;
        this.adapter = adapter;
        context = (Context) view;
    }

    public List<AbsItem<D>> makeItems(List<D> list, ItemLogic logic) {
        if (list == null)
            return null;
        List<AbsItem<D>> items = new ArrayList<>();
        for (D d : list) {
            AbsItem<D> item = template.newSelf();
            item.setLogic(logic);
            item.setContext(context);
            item.setAdapter(adapter);
            item.setData(d);
            item.readyTodo();
            items.add(item);
        }
        return items;
    }
}
