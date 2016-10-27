package com.micros.injection.module;

import android.app.Activity;
import android.content.Context;

import com.micros.injection.component.MicroActivityContext;

import dagger.Module;
import dagger.Provides;

/**
 * ClassName:
 * Description：
 * Author：Chengel_HaltuD
 * Date：2016/7/5 13:21
 * version：V1.0
 */
@Module
public class MicroActivityModule {

    private Activity mActivity;

    public MicroActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    Activity provideActivity() {
        return mActivity;
    }

    @Provides
    @MicroActivityContext
    Context providesContext() {
        return mActivity;
    }
}
