package com.surrus.galwaybus;

import android.util.Log;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.plugins.RxJavaObservableExecutionHook;

/**
 * Created by ivil0001 on 26/01/16.
 */
public class BetterExecutionHook extends RxJavaObservableExecutionHook {

    BetterExecutionInterface betterExecutionBridge;

    public BetterExecutionHook(BetterExecutionInterface betterExecutionBridge) {

        this.betterExecutionBridge = betterExecutionBridge;
    }

    @Override
    public <T> Observable.OnSubscribe<T> onCreate(Observable.OnSubscribe<T> f) {

        return super.onCreate(f);
    }

    @Override
    public <T> Observable.OnSubscribe<T> onSubscribeStart(Observable<? extends T> observableInstance, Observable.OnSubscribe<T> onSubscribe) {
Log.e("inaki","smsskksksksksk1111");
        onSubscribe.call(new Subscriber<T>() {
            @Override
            public void onCompleted() {
Log.e("inaki", "ssssss");
                betterExecutionBridge.onEnd();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("inaki", "aaaaa");

                betterExecutionBridge.onEnd();
            }

            @Override
            public void onNext(T t) {
                Log.e("inaki", "wwwweeeee");

            }
        });
        betterExecutionBridge.onStart();
        return onSubscribe;

    }

    @Override
    public <T> Subscription onSubscribeReturn(Subscription subscription) {

        return subscription;
    }

    @Override
    public <T> Throwable onSubscribeError(Throwable e) {

        return e;
    }

    @Override
    public <T, R> Observable.Operator<? extends R, ? super T> onLift(Observable.Operator<? extends R, ? super T> lift) {

        return super.onLift(lift);
    }
}