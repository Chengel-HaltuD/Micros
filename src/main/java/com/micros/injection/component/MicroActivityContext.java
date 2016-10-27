package com.micros.injection.component;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * ClassName:
 * Description：
 * Author：Chengel_HaltuD
 * Date：2016/7/5 13:21
 * version：V1.0
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface MicroActivityContext {
}
