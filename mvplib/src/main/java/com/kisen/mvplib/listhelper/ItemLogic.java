package com.kisen.mvplib.listhelper;

import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * 列表内Item逻辑处理
 * Created by huang on 2017/3/23.
 */

public interface ItemLogic {
    /**
     * 是否准备就绪
     */
    boolean isReady();

    /**
     * 条目的点击事件，如果isReady() 返回false，不会执行该方法
     *
     * @param adapter 适配器
     * @param item    点击的item
     */
    void onItemClick(RecyclerView.Adapter adapter, IAdapter item);

    /**
     * 设置Item选中
     */
    void setSelect(IAdapter item);

    void setSelectIds(String selectIds);

    String getSelectIds();

    List<IAdapter> getSelectItems();

    /**
     * 清空保存的所有数据
     */
    void clear();

    boolean isSelect(IAdapter item);
}
