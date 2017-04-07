package rcd27.github.com.stasyandex.presenter;


import rcd27.github.com.stasyandex.model.Model;
import rcd27.github.com.stasyandex.model.ModelImpl;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BasePresenter implements Presenter{

    protected Model responseData = new ModelImpl();
    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    protected void addSubscriprtion(Subscription subscription) {
        compositeSubscription.add(subscription);
    }

    @Override
    public void onStop() {
        compositeSubscription.clear();
    }
}
