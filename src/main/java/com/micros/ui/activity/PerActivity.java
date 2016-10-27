package com.micros.ui.activity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * ClassName: A scoping annotation to permit objects whose lifetime should
 *              conform to the life of the Activity to be memorised in the
 *              correct component.
 * Description：
 * Author：Chengel_HaltuD
 * Date：2016/7/1 13:51
 * version：V1.0
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
