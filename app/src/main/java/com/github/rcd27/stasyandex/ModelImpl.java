package com.github.rcd27.stasyandex;


import android.support.annotation.NonNull;

import com.github.rcd27.stasyandex.data.dictionary.DicResult;
import com.github.rcd27.stasyandex.data.translation.AvailableLanguages;
import com.github.rcd27.stasyandex.data.translation.ProbableLanguage;
import com.github.rcd27.stasyandex.data.translation.Translation;
import com.github.rcd27.stasyandex.dictionary.DictionaryAPI;
import com.github.rcd27.stasyandex.translation.TranslationAPI;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/*
Объединяет в себе логику получения JSON ответа с сервера, перевод его в DTO объекты.
 */

public class ModelImpl implements Model {

    private final Observable.Transformer schedulersTransformer;
    private final TranslationAPI translationAPI;
    private final DictionaryAPI dictionaryAPI;

    public ModelImpl(@NonNull TranslationAPI translationAPI,
                     @NonNull DictionaryAPI dictionaryAPI) {

        this.translationAPI = translationAPI;
        this.dictionaryAPI = dictionaryAPI;

        schedulersTransformer = o -> ((Observable) o) // если убрать каст, компилятор начинает моросить
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                // FIXME: а unsubscribeOn тоже нужен?
                .unsubscribeOn(Schedulers.io());
    }

    @Override
    public Observable<AvailableLanguages> getAvailableLanguages(String forLanguage) {
        return translationAPI.getAvailableLangs(forLanguage)
                .compose(applySchedulers());
    }

    @Override
    public Observable<ProbableLanguage> getProbableLanguage(String forText) {
        return translationAPI.getProbableLanguage(forText)
                .compose(applySchedulers());
    }

    @Override
    public Observable<Translation> getTranslation(String forText, String direction) {
        return translationAPI.getTranslation(forText, direction)
                .compose(applySchedulers());
    }

    @Override
    public Observable<DicResult> getDicResult(String forLanguage, String text, String uiLang) {
        return dictionaryAPI.getDicResultFor(forLanguage, text, uiLang);
    }

    @SuppressWarnings("unchecked")
    private <T> Observable.Transformer<T, T> applySchedulers() {
        return (Observable.Transformer<T, T>) schedulersTransformer;
    }
}
