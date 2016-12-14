package com.hpy1012.radiochef.base;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by hpy1012 on 2016/11/11.
 */

public class BasePresenterImpl<V> implements Presenter<V> {
    private V  mvpView;
    private CompositeSubscription mCompositeSubscription;


    @Override
    public void attachView(V view) {
        this.mvpView=view;
    }

    @Override
    public void detachView() {
        this.mvpView=null;
        onUnsubscribe();
    }

    //RXjava取消注册，以避免内存泄露
    public void onUnsubscribe() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    public void addSubscription(Observable observable, Subscriber subscriber) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }

        mCompositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }
}
