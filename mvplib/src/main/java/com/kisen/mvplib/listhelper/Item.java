package com.kisen.mvplib.listhelper;

import android.view.View;
import android.view.ViewGroup;

/**
 * @Title :
 * @Description :
 * @Version :
 * Created by huang on 2017/3/21.
 */

public interface Item {

    /**
     * 需要实现，返回对应Item的布局文件Id 如果返回0，则使用适配器默认布局
     *
     * @return 返回当前数据类对应布局
     */
    int getItemResId();

    /**
     * 必须实现，在数据类中直接将数据适配到通过BaseViewHolder获取到的视图中
     *
     * @param helper          用来获取Item的控件
     * @param adapterPosition 该Item在Adapter中的位置
     *                        {@link android.widget.BaseAdapter#getView(int, View, ViewGroup)}
     */
    void onBindViewHolder(BaseViewHolder helper, int adapterPosition);

    /**
     * 需要实现，默认返回0，同一列表中出现多种不同的布局时，必须返回不同的类型，
     * 如果返回相同的值，会因BaseViewHolder复用出现布局错乱，处理数据时异常
     * 在{@link Item#getItemResId()}中已经把对应的布局返回给适配器
     *
     * @return 返回当前自定义Item类型
     * {@link android.widget.BaseAdapter#getItemViewType(int)}
     */
    int getItemType();

    /**
     * 在Adapter中获取到的Item的位置数据
     *
     * @return item在adapter中的位置
     */
    int getItemPosition();
}
