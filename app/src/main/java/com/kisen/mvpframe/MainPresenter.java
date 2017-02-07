package com.kisen.mvpframe;

import android.app.Activity;
import android.widget.TextView;

import com.kisen.mvpframe.mvp.adapter.BaseViewHolder;
import com.kisen.mvpframe.mvp.model.IModel;
import com.kisen.mvpframe.mvp.presenter.ListPresenter;
import com.kisen.mvpframe.mvp.view.IView;

/**
 * 首页列表的实现类
 * Created by huang on 2017/2/7.
 */

public class MainPresenter extends ListPresenter {

    public MainPresenter(IView view) {
        super(view);
    }

    @Override
    protected IModel setupModel(IView view) {
        return new MainModel((Activity) view);
    }

    @Override
    public int getItemResId() {
        return R.layout.item_view;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        MainData data = (MainData) getList().get(position);
        TextView title = holder.getView(R.id.title);
        if (data != null) {
            title.setText(data.getTitle());
        }
    }
}
