package com.kisen.mvpframe.list;

import android.support.v7.widget.RecyclerView;

import com.kisen.mvplib.listhelper.ItemLogic;
import com.kisen.mvplib.listhelper.IAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 列表Item多选处理
 * Created by huang on 2017/3/21.
 */
public class MultiLogic implements ItemLogic {

    private List<IAdapter> mSelectItems = new ArrayList<>();
    private String mSelectIds;

    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public void onItemClick(RecyclerView.Adapter adapter, IAdapter item) {
        if (mSelectItems.contains(item)) {
            mSelectItems.remove(item);
        } else {
            mSelectItems.add(item);
        }
        adapter.notifyItemChanged(item.getItemPosition());
    }

    @Override
    public boolean isSelect(IAdapter item) {
        return mSelectItems.contains(item);
    }

    @Override
    public void setSelect(IAdapter item) {
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
    public List<IAdapter> getSelectItems() {
        return mSelectItems;
    }

    @Override
    public void clear() {
        mSelectItems.clear();
    }
}
