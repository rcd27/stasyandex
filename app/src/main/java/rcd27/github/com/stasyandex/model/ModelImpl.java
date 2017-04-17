package rcd27.github.com.stasyandex.model;


import javax.inject.Inject;
import javax.inject.Named;

import rcd27.github.com.stasyandex.Const;
import rcd27.github.com.stasyandex.fragments.dictionary.model.DictionaryAPI;
import rcd27.github.com.stasyandex.fragments.dictionary.model.dto.DicResultDTO;
import rcd27.github.com.stasyandex.fragments.translation.model.TranslateAPI;
import rcd27.github.com.stasyandex.fragments.translation.model.dto.AvailableLanguagesDTO;
import rcd27.github.com.stasyandex.fragments.translation.model.dto.ProbableLanguageDTO;
import rcd27.github.com.stasyandex.fragments.translation.model.dto.TranslationDTO;
import rx.Observable;
import rx.Scheduler;

/*
Объединяет в себе логику получения JSON ответа с сервера, перевод его в DTO объекты.
 */

public class ModelImpl implements Model {

    private final Observable.Transformer schedulersTransformer;

    @Inject
    TranslateAPI translateAPI = ApiModule.getTranslateApi();

    @Inject
    DictionaryAPI dictionaryAPI = ApiModule.getDictionaryApi();

    @Inject
    @Named(Const.UI_THREAD)
    Scheduler uiThread;

    @Inject
    @Named(Const.IO_THREAD)
    Scheduler ioThread;

    public ModelImpl() {
        schedulersTransformer = o -> ((Observable) o)
                .subscribeOn(ioThread)
                .observeOn(uiThread)
                .unsubscribeOn(ioThread);
    }

    //TODO разобраться с этими observeOn, subscribeOn
    @Override
    public Observable<AvailableLanguagesDTO> getAvailableLanguages(String forLanguage) {
        return translateAPI.getAvailableLangs(forLanguage)
                .compose(applySchedulers());
    }

    @Override
    public Observable<ProbableLanguageDTO> getProbableLanguage(String forText) {
        return translateAPI.getProbableLanguage(forText)
                .compose(applySchedulers());
    }

    @Override
    public Observable<TranslationDTO> getTranslation(String forText, String direction) {
        return translateAPI.getTranslation(forText, direction)
                .compose(applySchedulers());
    }

    @Override
    public Observable<DicResultDTO> getDicResult(String forLanguage, String text, String uiLang) {
        return dictionaryAPI.getDicResultFor(forLanguage, text, uiLang);
    }

    @SuppressWarnings("unchecked")
    private <T> Observable.Transformer<T, T> applySchedulers() {
        return (Observable.Transformer<T, T>) schedulersTransformer;
    }
}
