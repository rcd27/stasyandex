package rcd27.github.com.stasyandex.presenter;


import rcd27.github.com.stasyandex.model.Model;
import rcd27.github.com.stasyandex.model.ModelImpl;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/*
Базовый перзентер, включает в себя добавление subscription'а к составному subscription'у, очистку
последнего при onStop().
 */

public abstract class BasePresenter implements Presenter{

    /*Ответ сервера*/
    protected Model responseData = new ModelImpl();

    /*Составная подписка*/
    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    protected void addSubscriprtion(Subscription subscription) {
        compositeSubscription.add(subscription);
    }

    @Override
    public void onStop() {
        compositeSubscription.clear();
    }
}
