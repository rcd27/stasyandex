package com.github.rcd27.stasyandex.model;


import com.github.rcd27.stasyandex.di.DaggerAppComponent;
import com.github.rcd27.stasyandex.di.ModelModule;
import com.github.rcd27.stasyandex.model.dictionary.DictionaryAPI;
import com.github.rcd27.stasyandex.model.dictionary.dto.DicResult;
import com.github.rcd27.stasyandex.model.translation.TranslationAPI;
import com.github.rcd27.stasyandex.model.translation.dto.AvailableLanguages;
import com.github.rcd27.stasyandex.model.translation.dto.ProbableLanguage;
import com.github.rcd27.stasyandex.model.translation.dto.Translation;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

/*
Объединяет в себе логику получения JSON ответа с сервера, перевод его в DTO объекты.
 */

public class ModelImpl implements Model {

    private final Observable.Transformer schedulersTransformer;

    @Inject
    TranslationAPI translationAPI;

    @Inject
    DictionaryAPI dictionaryAPI;

    @Inject
    @Named(Const.UI_THREAD)
    Scheduler uiThread;

    @Inject
    @Named(Const.IO_THREAD)
    Scheduler ioThread;

    public ModelImpl() {
        DaggerAppComponent.builder().modelModule(new ModelModule())
                .build().inject(this);
        schedulersTransformer = o -> ((Observable) o) // если убрать каст, компилятор начинает моросить
                .subscribeOn(ioThread)
                .observeOn(uiThread)
                .unsubscribeOn(ioThread);
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
