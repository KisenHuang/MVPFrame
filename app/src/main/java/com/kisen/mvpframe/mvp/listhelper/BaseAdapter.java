package com.kisen.mvpframe.mvp.listhelper;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 适配器将工作转移到{@link Item}的实现类中
 * Created by huang on 2017/2/7.
 */

public class BaseAdapter<I extends Item> extends RecyclerView.Adapter<BaseViewHolder> {

    private List<I> mItems;
    private int itemPos;

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(mItems.get(itemPos).getItemResId(), parent, false);
        return new BaseViewHolder(v);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        itemPos = holder.getAdapterPosition();
        mItems.get(position).onBindViewHolder(holder, position);
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).getItemType();
    }

    @Override
    public int getItemCount() {
        return mItems == null ? 0 : mItems.size();
    }

    public void addData(List<I> items) {
        if (items == null)
            return;
        if (mItems == null) {
            mItems = items;
        } else {
            mItems.addAll(items);
        }
        notifyDataSetChanged();
    }

    public void clear(){
        mItems.clear();
        notifyDataSetChanged();
    }

    public I getItem(int position) {
        return mItems.get(position);
    }
}
