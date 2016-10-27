package com.micros.injection.presenter;
/**
 * ClassName: 处理业务需要哪些方法
 * Description：
 * Author：Chengel_HaltuD
 * Date：2016/7/1 16:42
 * version：V1.0
 */
public interface MicroBaseView<T> {

    void getDataSuccess(T model);

    void getDataFail(String msg);

    void showLoading();

    void hideLoading();

    /* void showNetworkErrorView();

    void hideNetworkErrorView();

    void showLoading();

    void hideLoading();

    void showEmptyView();

    void hideEmptyView();

    void showError(String message);*/
}
