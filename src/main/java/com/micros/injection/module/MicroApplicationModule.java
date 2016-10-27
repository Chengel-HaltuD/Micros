package com.micros.injection.module;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import com.micros.injection.component.MicroApplicationContext;

import javax.inject.Singleton;

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
public class MicroApplicationModule {
    protected final Application mApplication;

    public MicroApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @MicroApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    protected Resources provideResources() {
        return mApplication.getResources();
    }


//    @Provides
//    @Singleton
//    RibotsService provideRibotsService() {
//        return RibotsService.Creator.newRibotsService();
//    }

}
