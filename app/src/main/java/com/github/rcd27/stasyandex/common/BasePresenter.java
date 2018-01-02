package com.github.rcd27.stasyandex.common;


import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BasePresenter {

    protected final CompositeSubscription compositeSubscription;

    protected BasePresenter() {
        this.compositeSubscription = new CompositeSubscription();
    }

    protected void addSubscription(Subscription subscription) {
        compositeSubscription.add(subscription);
    }

    void onStop() {
        compositeSubscription.clear();
    }
}
