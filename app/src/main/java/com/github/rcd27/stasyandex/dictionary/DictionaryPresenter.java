package com.github.rcd27.stasyandex.dictionary;


import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

import com.github.rcd27.stasyandex.common.BasePresenter;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DictionaryPresenter extends BasePresenter implements DictionaryContract.Presenter {

    private final DictionaryContract.View view;
    private final DictionaryContract.Api api;

    public DictionaryPresenter(DictionaryContract.View view, @NonNull DictionaryContract.Api api) {
        this.view = view;
        this.api = api;
    }

    public void getDictionaryResponseFor(@NonNull String direction, @NonNull String text) {
        addSubscription(getSubscriptionForDictionaryDefinition(direction, text));
    }

    @SuppressLint("RxSubscribeOnError")
    @NonNull
    private Subscription getSubscriptionForDictionaryDefinition(String direction, String text) {
        return api.getDicResultFor(direction, text, "ru")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable -> view.showEmpty())
                .subscribe(dicResult -> {
                    if (!dicResult.definitionListIsEmptyOrNull()) {
                        view.showDefinition(dicResult.getDefinition());
                        view.showDictionaryItems(dicResult.getElementsList());
                        view.showError("«Реализовано с помощью сервиса «Яндекс.Словарь»");
                    } else {
                        view.showEmpty();
                    }
                });
    }
}