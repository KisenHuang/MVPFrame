package com.kisen.mvpframe;

import com.kisen.mvpframe.mvp.bean.IData;

/**
 * @Title :
 * @Description :
 * @Version :
 * Created by huang on 2017/2/7.
 */

public class MainData implements IData {

    private String title;

    public MainData(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
