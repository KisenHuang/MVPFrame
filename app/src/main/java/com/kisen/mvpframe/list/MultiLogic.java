package com.kisen.mvpframe.list;

import android.support.v7.widget.RecyclerView;

import com.kisen.mvpframe.mvp.listhelper.ItemLogic;
import com.kisen.mvpframe.mvp.listhelper.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title :
 * @Description :
 * @Version :
 * Created by huang on 2017/3/21.
 */
public class MultiLogic implements ItemLogic {

    private List<Item> mSelectItems = new ArrayList<>();
    private String mSelectIds;

    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public void onItemClick(RecyclerView.Adapter adapter, Item item) {
        if (mSelectItems.contains(item)) {
            mSelectItems.remove(item);
        } else {
            mSelectItems.add(item);
        }
        adapter.notifyItemChanged(item.getItemPosition());
    }

    @Override
    public boolean isSelect(Item item) {
        return mSelectItems.contains(item);
    }

    @Override
    public void setSelect(Item item) {
        mSelectItems.add(item);
    }

    @Override
    public void setSelectIds(String selectIds) {
        mSelectIds = selectIds;
    }

    @Override
    public String getSelectIds() {
        return mSelectIds;
    }

    @Override
    public List<Item> getSelectItems() {
        return mSelectItems;
    }

    @Override
    public void clear() {
        mSelectItems.clear();
    }
}
