package com.micros.injection.presenter;

/**
 * ClassName:
 * Description：
 * Author：Chengel_HaltuD
 * Date：2016/7/1 16:58
 * version：V1.0
 */
public interface MicroApiCallback<T> {

    void onSuccess(T model);

    void onFailure(int code, String msg);

    void onCompleted();

}