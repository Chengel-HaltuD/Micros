package com.micros.injection.component;

import android.app.Application;
import android.content.Context;

import com.micros.injection.module.MicroApplicationModule;

import javax.inject.Singleton;

import dagger.Component;
/**
 * ClassName:
 * Description：
 * Author：Chengel_HaltuD
 * Date：2016/7/5 13:21
 * version：V1.0
 */
@Singleton
@Component(modules = MicroApplicationModule.class)
public interface MicroApplicationComponent {


//    void inject(SyncService syncService);
//
//    @ApplicationContext Context context();
//    Application application();
//    RibotsService ribotsService();
//    PreferencesHelper preferencesHelper();
//    DatabaseHelper databaseHelper();
//    DataManager dataManager();
//    RxEventBus eventBus();
}
