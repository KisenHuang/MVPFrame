package com.kisen.mvplib.listhelper;

import android.view.View;

import com.kisen.mvplib.bean.Data;

/**
 *
 * Created by huang on 2017/4/26.
 */
public interface Interact<D extends Data> {
    /**
     * 用于更新UI样式
     */
    void onRefreshViewStyle();

    /**
     * 得到Data数据，显示在Item上
     *
     * @param helper item UI持有对象
     * @see IAdapter setViewData(Context context,BaseViewHolder helper, int adapterPosition)
     */
    void setViewData(BaseViewHolder helper);

    /**
     * 设置Item是否可以点击
     */
    boolean itemEnable();

    /**
     * 条目点击事件。
     * {@link Interact#itemEnable()}必须返回true，这个方法才会被调用
     *
     * @param v item对应View
     */
    void onItemClick(View v);

    /**
     * 工厂方法创建Item时调用的方法
     *
     * @return 返回一个新的Item实例
     */
    Item<D> newSelf();

    /**
     * Item被创建时，设置完数据后调用，
     * 在{@link Item#onBindViewHolder(BaseViewHolder, int)}之前被调用，
     * 只被调用一次
     */
    void readyTodo();
}
