package com.micros.ui.application;

import android.app.Application;
import android.content.Context;

import com.micros.injection.component.DaggerMicroApplicationComponent;
import com.micros.injection.component.MicroApplicationComponent;

/**
 * ClassName:
 * Description：
 * Author：Chengel_HaltuD
 * Date：2016/7/1 13:51
 * version：V1.0
 */
public class MicroApplication extends Application  {

    MicroApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

    }

    public static MicroApplication get(Context context) {
        return (MicroApplication) context.getApplicationContext();
    }

    public MicroApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
//            mApplicationComponent = DaggerMicroApplicationComponent.builder()
//                    .microApplicationModule(new MicroApplicationModule(this))
//                    .build();
        }
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(MicroApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
