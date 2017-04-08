package rcd27.github.com.stasyandex.model;


import rcd27.github.com.stasyandex.model.api.DictionaryAPI;
import rcd27.github.com.stasyandex.model.api.TranslateAPI;
import rcd27.github.com.stasyandex.model.api.ApiModule;
import rcd27.github.com.stasyandex.model.dto.dictionary.DicResultDTO;
import rcd27.github.com.stasyandex.model.dto.translate.AvailableLanguagesDTO;
import rcd27.github.com.stasyandex.model.dto.translate.ProbableLanguageDTO;
import rcd27.github.com.stasyandex.model.dto.translate.TranslationDTO;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/*
Объединяет в себе логику получения JSON ответа с сервера, перевод его в DTO объекты.
 */

public class ModelImpl implements Model {

    private final Observable.Transformer schedulersTransformer;
    private TranslateAPI translateAPI = ApiModule.getTranslateApi();
    private DictionaryAPI dictionaryAPI = ApiModule.getDictionaryApi();

    //TODO интересно, чё это.
    public ModelImpl() {
        schedulersTransformer = o -> ((Observable) o)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io());
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
