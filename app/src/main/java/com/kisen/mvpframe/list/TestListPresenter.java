package com.kisen.mvpframe.list;

import com.kisen.mvplib.listhelper.Item;
import com.kisen.mvplib.model.ModelResult;
import com.kisen.mvplib.model.ResultCallback;
import com.kisen.mvplib.presenter.BaseListPresenter;
import com.kisen.mvplib.util.RequestParam;
import com.kisen.mvplib.view.View;

import java.util.ArrayList;

/**
 * @Title :
 * @Description :
 * @Version :
 * Created by huang on 2017/3/22.
 */
public class TestListPresenter extends BaseListPresenter<ListData> {

    private ListModel model;

    @Override
    public void attachView(View view) {
        super.attachView(view);
        setItemLogic(new MultiLogic());
        model = new ListModel();
    }

    @Override
    protected Item<ListData> setupItemTemplate() {
        return new ListItem();
    }


    public void setSelect(String ids) {
        itemLogic.setSelectIds(ids);
    }

    public void onRefresh(int resultCode, RequestParam param) {
        getView().openLoadingAnim();
        model.onRefresh(resultCode, param, new ResultCallback() {
            @Override
            public void onComplete(ModelResult result) {
                getView().closeLoadingAnim();
                getView().onModelComplete(result);
                ArrayList<ListData> source = result.getBundle().getParcelableArrayList("result");
                notifyAfterLoad(source);
            }
        });
    }

    protected void onLoad(RequestParam param) {
    }

    public void commit(int resultCode) {
        model.commit(resultCode, (MultiLogic) itemLogic, new DefResultCallback());
    }

}
