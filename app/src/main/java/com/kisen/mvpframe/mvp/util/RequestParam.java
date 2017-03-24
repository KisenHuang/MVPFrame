package com.kisen.mvpframe.mvp.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Title :
 * @Description :
 * @Version :
 * Created by huang on 2017/3/22.
 */

public class RequestParam {

    private Map<String, Object> mParams;

    public RequestParam() {
        this(null);
    }

    public RequestParam(Map<String, Object> params) {
        if (params == null) {
            mParams = new HashMap<>();
        } else {
            mParams = new HashMap<>(params);
        }
    }

    public void put(String key, Object value) {
        mParams.put(key, value);
    }

    public boolean isEmpty() {
        return mParams == null || mParams.isEmpty();
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        Set<String> keys = mParams.keySet();
        for (String key : keys) {
            builder.append(key)
                    .append("=")
                    .append(mParams.get(key))
                    .append("&");
        }
        builder.deleteCharAt(builder.length());
        return builder.toString();
    }
}
