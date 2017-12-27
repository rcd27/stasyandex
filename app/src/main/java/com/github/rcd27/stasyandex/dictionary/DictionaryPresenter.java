package com.github.rcd27.stasyandex.dictionary;


import android.util.Log;

import javax.inject.Inject;

import com.github.rcd27.stasyandex.data.dictionary.DicResult;
import com.github.rcd27.stasyandex.BasePresenter;

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

    //TODO FIXME есди в этом методе используется другой с такими же параметрами, зачем он нужен?!
    public void getDictionaryResponseFor(String direction, String text) {
        addSubscription(getSubscriptionForDictionaryDefention(direction, text));
    }

    private Subscription getSubscriptionForDictionaryDefention(String direction, String text) {
        return responseData.getDicResult(direction, text, "ru")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .firstOrDefault(new DicResult())
                .doOnError(throwable -> {
                    view.showEmpty();
                    Log.w(TAG, "Retrofit/rxJava error!");
                    throwable.printStackTrace();
                })
                .doOnNext(dicResult -> {
                    if (!dicResult.definitionListIsEmptyOrNull()) {
                        view.showDefinition(dicResult.getDefinition());
                        view.showDictionaryItems(dicResult.getElementsList());
                        view.showError("«Реализовано с помощью сервиса «Яндекс.Словарь»");
                    } else {
                        view.showEmpty();
                    }
                })
                .subscribe();
    }
}
