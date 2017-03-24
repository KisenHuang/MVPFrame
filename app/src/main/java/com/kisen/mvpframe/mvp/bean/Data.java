package com.kisen.mvpframe.mvp.bean;

import android.os.Parcelable;

/**
 * 标示性父类，没有实际意义
 * <p>
 * 使用第三方解析工具后，被解析的数据类不能被混淆
 * 在混淆代码时，数据类只需要混淆Data子类就可以了
 * </p>
 * Created by huang on 2017/2/7.
 */

public abstract class Data implements Parcelable{

}
