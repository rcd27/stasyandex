package rcd27.github.com.stasyandex.presenter;


import javax.inject.Inject;

import rcd27.github.com.stasyandex.di.DaggerAppComponent;
import rcd27.github.com.stasyandex.di.PresenterModule;
import rcd27.github.com.stasyandex.model.Model;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/*
Базовый перзентер, включает в себя добавление subscription'а к составному subscription'у, очистку
последнего при onStop().
 */

public abstract class BasePresenter implements Presenter {

    /*Ответ сервера*/
    @Inject
    protected Model responseData;

    /*Составная подписка*/
    @Inject
    protected CompositeSubscription compositeSubscription;

    //TODO FIXME: вот для чего эти танцы с бубнами? Тоже не ясно.
    public BasePresenter() {
        DaggerAppComponent.builder()
                .presenterModule(new PresenterModule())
                .build().inject(this);
    }

    protected void addSubscription(Subscription subscription) {
        compositeSubscription.add(subscription);
    }

    @Override
    public void onStop() {
        compositeSubscription.clear();
    }
}
