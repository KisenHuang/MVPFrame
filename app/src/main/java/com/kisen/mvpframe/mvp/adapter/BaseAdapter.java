package com.kisen.mvpframe.mvp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kisen.mvpframe.mvp.presenter.ListPresenter;

/**
 * 适配器将工作转移到{@link ListPresenter}的实现类中
 * Created by huang on 2017/2/7.
 */

public class BaseAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private final ListPresenter presenter;

    public BaseAdapter(ListPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(presenter.getItemResId(), parent, false);
        return new BaseViewHolder(v);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        presenter.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return presenter.getList().size();
    }
}
