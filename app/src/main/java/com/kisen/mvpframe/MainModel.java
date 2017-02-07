package com.kisen.mvpframe;

import android.content.Context;
import android.os.Handler;

import com.kisen.mvpframe.mvp.bean.IData;
import com.kisen.mvpframe.mvp.model.BaseModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title :
 * @Description :
 * @Version :
 * Created by huang on 2017/2/7.
 */
public class MainModel extends BaseModel {



    private Handler thread = new Handler();

    public MainModel(Context context) {
        super(context);
    }

    @Override
    public void load(final OnLoadListener listener) {
        final List<IData> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(new MainData("标题" + i));
        }
        thread.postDelayed(new Runnable() {
            @Override
            public void run() {
                listener.onComplete(list);
            }
        }, 100);
    }
}
