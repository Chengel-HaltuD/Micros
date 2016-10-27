package com.micros.ui.activity;


import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.micros.injection.component.DaggerMicroActivityComponent;
import com.micros.ui.application.MicroApplication;
import com.micros.injection.component.MicroActivityComponent;

import butterknife.ButterKnife;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * ClassName:
 * Description：
 * Author：Chengel_HaltuD
 * Date：2016/7/1 13:51
 * version：V1.0
 */
public class MicroActivity extends AppCompatActivity {



    private MicroActivityComponent mActivityComponent;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }


    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        ButterKnife.bind(this);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        ButterKnife.bind(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public MicroActivityComponent getActivityComponent() {
        if (mActivityComponent == null) {
//            mActivityComponent = DaggerMicroActivityComponent.builder()
//                    .microActivityModule(new MicroActivityModule(this))
//                    .microApplicationComponent(MicroApplication.get(this).getComponent())
//                    .build();
        }
        return mActivityComponent;
    }

    @Override
    protected void onDestroy() {
        onUnsubscribe();
        super.onDestroy();
    }

    private CompositeSubscription mCompositeSubscription;

    /**
     * 取消注册，以避免内存泄露
     */
    public void onUnsubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();//取消注册，以避免内存泄露
        }
    }

    /**
     * 添加注册
     *
     * @param subscription
     */
    public void addSubscription(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

}
