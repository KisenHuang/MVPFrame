package com.kisen.mvplib.listhelper;

import android.content.Context;
import android.view.View;

import com.kisen.mvplib.bean.Data;

/**
 * 自定义Item控制类
 * <p>
 * 将Adapter分成Item+Logic：
 * Item表示View，Logic表示Item里的逻辑处理，数据源由Model获取
 * </p>
 * <p>
 * {@link AbsItem#itemEnable()} 如果开启Item点击事件
 * {@link Item#getItemResId()} Item的布局中最好不要出现Button类控件
 * 可能会出现一些bug
 * </p>
 */
public abstract class AbsItem<D extends Data> implements Item, View.OnClickListener {

    protected D data;
    protected ItemLogic logic;
    protected BaseAdapter adapter;
    protected int position;
    protected Context mContext;

    @Override
    public void onBindViewHolder(BaseViewHolder helper, int adapterPosition) {
        position = adapterPosition;
        setViewData(helper);
        helper.convertView.setEnabled(itemEnable());
        helper.convertView.setOnClickListener(this);
        onRefreshViewStyle();
    }

    @Override
    public int getItemType() {
        //默认返回 0，可重写
        return 0;
    }

    @Override
    public int getItemPosition() {
        return position;
    }

    @Override
    public void onClick(View v) {
        if (logic != null && logic.isReady()) {
            logic.onItemClick(adapter, this);
        }
        onItemClick(v);
    }

    /**
     * 用于更新UI样式
     */
    protected abstract void onRefreshViewStyle();

    /**
     * 得到Data数据，显示在Item上
     *
     * @param helper item UI持有对象
     * @see Item setViewData(Context context,BaseViewHolder helper, int adapterPosition)
     */
    protected abstract void setViewData(BaseViewHolder helper);

    /**
     * 设置Item是否可以点击
     */
    protected abstract boolean itemEnable();

    /**
     * Item 点击事件。
     * {@link AbsItem#itemEnable()}必须返回true，这个方法才会被调用
     *
     * @param v item对应View
     */
    protected abstract void onItemClick(View v);

    /**
     * 工厂方法创建Item时调用的方法
     *
     * @return 返回一个新的Item实例
     */
    public abstract AbsItem<D> newSelf();

    /**
     * Item被创建时，设置完数据后调用，
     * 在{@link AbsItem#onBindViewHolder(BaseViewHolder, int)}之前被调用，
     * 只被调用一次
     */
    public abstract void readyTodo();

    /**
     * 返回Item持有数据
     */
    public D getData() {
        return data;
    }

    /**
     * 给Item设置数据
     */
    public void setData(D data) {
        this.data = data;
    }

    /**
     * 设置Adapter
     */
    public void setAdapter(BaseAdapter adapter) {
        this.adapter = adapter;
    }

    /**
     * 设置处理逻辑
     */
    public void setLogic(ItemLogic logic) {
        this.logic = logic;
    }

    /**
     * 设置上下文
     */
    public void setContext(Context context) {
        mContext = context;
    }
}
