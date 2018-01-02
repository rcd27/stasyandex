package com.github.rcd27.stasyandex.dictionary;


import android.support.annotation.NonNull;
import android.util.Log;

import com.github.rcd27.stasyandex.Model;
import com.github.rcd27.stasyandex.common.BasePresenter;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DictionaryPresenter extends BasePresenter implements DictionaryContract.Presenter {

    private final String TAG = getClass().getSimpleName();

    private final DictionaryContract.View view;
    private final Model model;

    public DictionaryPresenter(DictionaryContract.View view, @NonNull Model model) {
        this.view = view;
        this.model = model;
    }

    public void getDictionaryResponseFor(@NonNull String direction, @NonNull String text) {
        addSubscription(getSubscriptionForDictionaryDefinition(direction, text));
    }

    @NonNull
    private Subscription getSubscriptionForDictionaryDefinition(String direction, String text) {
        return model.getDicResult(direction, text, "ru")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .doOnError(throwable -> {
                    view.showEmpty();
                    Log.w(TAG, "Retrofit/rxJava error!");
                    throwable.printStackTrace();
                })
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