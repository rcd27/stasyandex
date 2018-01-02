package com.github.rcd27.stasyandex;


import android.support.annotation.NonNull;

import com.github.rcd27.stasyandex.data.dictionary.DicResult;
import com.github.rcd27.stasyandex.data.translation.AvailableLanguages;
import com.github.rcd27.stasyandex.data.translation.ProbableLanguage;
import com.github.rcd27.stasyandex.data.translation.Translation;
import com.github.rcd27.stasyandex.dictionary.DictionaryContract;
import com.github.rcd27.stasyandex.translation.TranslationContract;

import rx.Single;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ModelImpl implements Model {

    private final Single.Transformer ioToMainThread;
    private final TranslationContract.Api translationAPI;
    private final DictionaryContract.Api dictionaryAPI;

    public ModelImpl(@NonNull TranslationContract.Api translationAPI,
                     @NonNull DictionaryContract.Api dictionaryAPI) {

        this.translationAPI = translationAPI;
        this.dictionaryAPI = dictionaryAPI;

        //noinspection RedundantCast
        ioToMainThread = o -> ((Single) o) // если убрать каст, компилятор начинает моросить
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<AvailableLanguages> getAvailableLanguages(String forLanguage) {
        return translationAPI.getAvailableLangs(forLanguage)
                .compose(applySchedulers());
    }

    @Override
    public Single<ProbableLanguage> getProbableLanguage(String forText) {
        return translationAPI.getProbableLanguage(forText)
                .compose(applySchedulers());
    }

    @Override
    public Single<Translation> getTranslation(String forText, String direction) {
        return translationAPI.getTranslation(forText, direction)
                .compose(applySchedulers());
    }

    @Override
    public Single<DicResult> getDicResult(String forLanguage, String text, String uiLang) {
        return dictionaryAPI.getDicResultFor(forLanguage, text, uiLang);
    }

    @SuppressWarnings("unchecked")
    private <T> Single.Transformer<T, T> applySchedulers() {
        return (Single.Transformer<T, T>) ioToMainThread;
    }
}
