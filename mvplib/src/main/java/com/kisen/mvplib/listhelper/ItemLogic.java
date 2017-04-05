package com.kisen.mvplib.listhelper;

import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * @Title :
 * @Description :
 * @Version :
 * Created by huang on 2017/3/23.
 */

public interface ItemLogic {
    /**
     * 是否准备就绪
     */
    boolean isReady();

    /**
     * Item 的点击事件，如果isReady() 返回false，不会执行该方法
     *
     * @param adapter 适配器
     * @param item    点击的item
     */
    void onItemClick(RecyclerView.Adapter adapter, Item item);

    /**
     * 设置Item选中
     */
    void setSelect(Item item);

    void setSelectIds(String selectIds);

    String getSelectIds();

    List<Item> getSelectItems();

    /**
     * 清空保存的所有数据
     */
    void clear();

    boolean isSelect(Item item);
}
