package com.github.rcd27.stasyandex.dictionary;


import android.support.annotation.NonNull;
import android.util.Log;

import com.github.rcd27.stasyandex.Model;
import com.github.rcd27.stasyandex.common.BasePresenter;
import com.github.rcd27.stasyandex.data.dictionary.DicResult;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DictionaryPresenter extends BasePresenter {

    private final String TAG = getClass().getSimpleName();

    private final DictionaryView view;
    private final Model model;

    public DictionaryPresenter(DictionaryView view, @NonNull Model model) {
        this.view = view;
        this.model = model;
    }

    //TODO FIXME есди в этом методе используется другой с такими же параметрами, зачем он нужен?!
    public void getDictionaryResponseFor(String direction, String text) {
        addSubscription(getSubscriptionForDictionaryDefinition(direction, text));
    }

    private Subscription getSubscriptionForDictionaryDefinition(String direction, String text) {
        return model.getDicResult(direction, text, "ru")
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
