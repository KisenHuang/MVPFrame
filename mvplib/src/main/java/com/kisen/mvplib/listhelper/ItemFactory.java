package com.kisen.mvplib.listhelper;

import android.content.Context;
import android.support.annotation.Nullable;

import com.kisen.mvplib.bean.Data;
import com.kisen.mvplib.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * item工厂类
 * Created by huang on 2017/3/3.
 */

public class ItemFactory<D extends Data> {

    private Item<D> template;
    private BaseAdapter<Item<D>> adapter;
    private Context context;

    public ItemFactory(View view, Item<D> template, BaseAdapter<Item<D>> adapter) {
        this.template = template;
        this.adapter = adapter;
        context = (Context) view;
    }

    @Nullable
    public List<Item<D>> makeItems(List<D> list, ItemLogic logic) {
        if (list == null)
            return null;
        List<Item<D>> items = new ArrayList<>();
        for (D d : list) {
            Item<D> item = template.newSelf();
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
