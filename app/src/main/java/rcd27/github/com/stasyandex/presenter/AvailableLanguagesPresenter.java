package rcd27.github.com.stasyandex.presenter;

import android.util.Log;

import rcd27.github.com.stasyandex.model.Model;
import rcd27.github.com.stasyandex.model.ModelImpl;
import rcd27.github.com.stasyandex.model.data.AvailableLanguages;
import rcd27.github.com.stasyandex.view.View;
import rx.Observer;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

public class AvailableLanguagesPresenter implements Presenter {
    private final String TAG = this.getClass().getSimpleName();

    private Model model = new ModelImpl();

    private View view;
    private Subscription subscription = Subscriptions.empty();

    public AvailableLanguagesPresenter(View view) {
        this.view = view;
    }

    @Override
    public void onGetDirsButtonClick() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }

        subscription = model.getAvailableLanguages(view.forLanguage())
                .subscribe(new Observer<AvailableLanguages>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "subscription : onCompleted()");
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e.getMessage());
                        Log.i(TAG, "subscription : onError()");
                    }

                    @Override
                    public void onNext(AvailableLanguages responseFromServer) {
                        if (null != responseFromServer && !responseFromServer.isEmpty()) {
                            view.showAvailableLanguages(responseFromServer);
                            Log.i(TAG, "responseFromServer = " + responseFromServer);
                        } else {
                            view.showEmptyAvaialableLanguages();
                            Log.i(TAG, " :Response is empty");
                        }
                    }
                });
    }

    @Override
    public void onStop() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
            Log.i(TAG, "onStop()");
        }
    }
}
