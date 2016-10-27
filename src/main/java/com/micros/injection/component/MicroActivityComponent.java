package com.micros.injection.component;

import com.micros.injection.module.MicroActivityModule;
import com.micros.ui.activity.PerActivity;

import dagger.Component;
/**
 * ClassName: This component inject dependencies to all Activities across the application
 * Description：
 * Author：Chengel_HaltuD
 * Date：2016/7/5 13:21
 * version：V1.0
 */
@PerActivity
@Component(dependencies = MicroApplicationComponent.class, modules = MicroActivityModule.class)
public interface MicroActivityComponent {

//    void inject(MainActivity mainActivity);
}
