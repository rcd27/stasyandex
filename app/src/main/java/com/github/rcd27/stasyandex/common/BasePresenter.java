package com.github.rcd27.stasyandex.common;


import com.github.rcd27.stasyandex.Presenter;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/*
Базовый перзентер, включает в себя добавление subscription'а к составному subscription'у, очистку
последнего при onStop().
 */

public abstract class BasePresenter implements Presenter {

    protected final CompositeSubscription compositeSubscription;

    protected BasePresenter() {
        this.compositeSubscription = new CompositeSubscription();
    }

    protected void addSubscription(Subscription subscription) {
        compositeSubscription.add(subscription);
    }

    @Override
    public void onStop() {
        compositeSubscription.clear();
    }
}
