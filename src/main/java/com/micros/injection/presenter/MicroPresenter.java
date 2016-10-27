package com.micros.injection.presenter;

/**
 * ClassName:
 * Description：
 * Author：Chengel_HaltuD
 * Date：2016/7/1 16:42
 * version：V1.0
 */
public interface MicroPresenter<V> {

    void attachView(V view);

    void detachView();

}
