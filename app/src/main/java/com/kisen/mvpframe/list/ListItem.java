package com.kisen.mvpframe.list;

import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.kisen.mvpframe.R;
import com.kisen.mvplib.listhelper.Item;
import com.kisen.mvplib.listhelper.BaseViewHolder;

/**
 * 首页Item样式
 * Created by huang on 2017/3/21.
 */
public class ListItem extends Item<ListData> {

    private TextView title;

    @Override
    public void setViewData(BaseViewHolder helper) {
        title = helper.getView(R.id.title);
        title.setText(data.getTitle());
    }

    @Override
    public void onRefreshViewStyle() {
        if (logic.isSelect(this)) {
            title.setBackgroundColor(ContextCompat.getColor(mContext, R.color.green));
        } else {
            title.setBackgroundColor(ContextCompat.getColor(mContext, R.color.gray));
        }
    }

    @Override
    public void readyTodo() {
        String selectIds = logic.getSelectIds();
        //for test
        if (selectIds.equals(String.valueOf(data.getId()))) {
            logic.setSelect(this);
        }
//        if (selectIds.contains(String.valueOf(data.getId()))) {
//            logic.setSelect(this);
//        }
    }

    @Override
    public boolean itemEnable() {
        return true;
    }

    @Override
    public void onItemClick(View v) {
        Toast.makeText(mContext, "click IAdapter " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public ListItem newSelf() {
        return new ListItem();
    }

    @Override
    public int getItemResId() {
        return R.layout.item_view;
    }
}
