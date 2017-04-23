package rcd27.github.com.stasyandex.presenter.dictionary;


import android.util.Log;

import javax.inject.Inject;

import rcd27.github.com.stasyandex.model.dictionary.dto.Definition;
import rcd27.github.com.stasyandex.presenter.BasePresenter;
import rcd27.github.com.stasyandex.view.dictionary.DictionaryView;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DictionaryPresenter extends BasePresenter {

    private final String TAG = getClass().getSimpleName();

    private DictionaryView view;

    @Inject
    public DictionaryPresenter() {
    }

    public DictionaryPresenter(DictionaryView view) {
        super();
        this.view = view;
    }

    public void onGetDictionaryResponse(String text) {
        addSubscription(getSubscriptionForDictionaryDefention(text));
    }

    //TODO запилить направление перевода
    private Subscription getSubscriptionForDictionaryDefention(String text) {
        return responseData.getDicResult("ru-en", text, "ru")
                .flatMap(response -> Observable.from(response.getDefinition()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .firstOrDefault(new Definition())
                .subscribe(new Observer<Definition>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "subscription: onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.w(TAG, "subscription: onError()");
                    }

                    @Override
                    public void onNext(Definition definition) {
                        view.showDef(definition);
                        Log.i(TAG, "subscription: onNext()");
                    }
                });
    }
}
