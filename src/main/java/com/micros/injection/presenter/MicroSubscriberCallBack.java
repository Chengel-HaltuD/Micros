package com.micros.injection.presenter;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
/**
 * ClassName:
 * Description：
 * Author：Chengel_HaltuD
 * Date：2016/7/1 16:58
 * version：V1.0
 */
public class MicroSubscriberCallBack<T> extends Subscriber<T> {
    private MicroApiCallback<T> microApiCallback;

    public MicroSubscriberCallBack(MicroApiCallback<T> microApiCallback) {
        this.microApiCallback = microApiCallback;
    }

    public MicroSubscriberCallBack() {
    }

    @Override
    public void onCompleted() {
        microApiCallback.onCompleted();
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            //httpException.response().errorBody().string()
            int code = httpException.code();
            String msg = httpException.getMessage();
            if (code == 504) {
                msg = "网络不给力";
            }
            microApiCallback.onFailure(code, msg);
        } else {
            microApiCallback.onFailure(0, e.getMessage());
        }
        microApiCallback.onCompleted();
    }

    @Override
    public void onNext(T t) {
        microApiCallback.onSuccess(t);
    }
}

