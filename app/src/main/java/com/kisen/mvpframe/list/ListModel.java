package com.kisen.mvpframe.list;

import android.os.Bundle;
import android.os.Handler;

import com.kisen.mvpframe.mvp.listhelper.Item;
import com.kisen.mvpframe.mvp.model.ModelResult;
import com.kisen.mvpframe.mvp.model.ResultCallback;
import com.kisen.mvpframe.mvp.util.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title :
 * @Description :
 * @Version :
 * Created by huang on 2017/3/23.
 */
public class ListModel {

    public void onRefresh(final int resultCode, RequestParam param, final ResultCallback callback) {
        final ArrayList<ListData> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(new ListData(i, "标题 " + i));
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ModelResult result = new ModelResult(resultCode);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("result", list);
                result.setBundle(bundle);
                callback.onComplete(result);
            }
        }, 500);
    }

    public void commit(int resultCode, MultiLogic logic, ResultCallback callback) {
        StringBuilder builder = new StringBuilder();
        List<Item> select = logic.getSelectItems();
        for (Item item : select) {
            ListItem listItem = (ListItem) item;
            builder.append(listItem.getItemPosition());
        }
        ModelResult result = new ModelResult(resultCode);
        Bundle bundle = new Bundle();
        bundle.putString("ids", builder.toString());
        result.setBundle(bundle);
        callback.onComplete(result);
    }
}
