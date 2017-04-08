package rcd27.github.com.stasyandex.model;


import rcd27.github.com.stasyandex.model.api.ApiInterface;
import rcd27.github.com.stasyandex.model.api.ApiModule;
import rcd27.github.com.stasyandex.model.dto.AvailableLanguagesDTO;
import rcd27.github.com.stasyandex.model.dto.ProbableLanguageDTO;
import rcd27.github.com.stasyandex.model.dto.TranslationDTO;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/*
Объединяет в себе логику получения JSON ответа с сервера, перевод его в DTO объекты.
 */

public class ModelImpl implements Model {

    private final Observable.Transformer schedulersTransformer;
    private ApiInterface apiInterface = ApiModule.getApiInterface();

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
        return apiInterface.getAvailableLangs(forLanguage)
                .compose(applySchedulers());
    }

    @Override
    public Observable<ProbableLanguageDTO> getProbableLanguage(String forText) {
        return apiInterface.getProbableLanguage(forText)
                .compose(applySchedulers());
    }

    @Override
    public Observable<TranslationDTO> getTranslation(String forText, String direction) {
        return apiInterface.getTranslation(forText, direction)
                .compose(applySchedulers());
    }

    @SuppressWarnings("unchecked")
    private <T> Observable.Transformer<T, T> applySchedulers() {
        return (Observable.Transformer<T, T>) schedulersTransformer;
    }
}
