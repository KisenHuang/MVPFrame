package com.kisen.mvpframe;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.kisen.mvpframe.mvp.adapter.BaseViewHolder;
import com.kisen.mvpframe.mvp.bean.IData;
import com.kisen.mvpframe.mvp.model.BaseModel;
import com.kisen.mvpframe.mvp.model.IModel;
import com.kisen.mvpframe.mvp.presenter.ListPresenter;
import com.kisen.mvpframe.mvp.view.IView;

import java.util.List;

/**
 * 首页列表的实现类
 * Created by huang on 2017/2/7.
 */

public class MainPresenter extends ListPresenter {

    private IView mainView;

    private IModel model;

    public MainPresenter(IView view) {
        mainView = view;
        model = new MainModel((Activity)view);
    }

    @Override
    public void fetch() {
        mainView.onPreLoad();
        model.load(new BaseModel.OnLoadListenerImpl() {
            @Override
            public void onComplete(List<IData> list) {
                setList(list);
                mainView.onLoadData(MainPresenter.this);
            }
        });
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
