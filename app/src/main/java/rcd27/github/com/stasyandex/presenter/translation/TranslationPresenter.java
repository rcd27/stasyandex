package rcd27.github.com.stasyandex.presenter.translation;


import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import rcd27.github.com.stasyandex.presenter.BasePresenter;
import rcd27.github.com.stasyandex.presenter.visualobject.Translation;
import rcd27.github.com.stasyandex.view.translation.TranslationView;
import rx.Observer;
import rx.Subscription;
import rx.plugins.RxJavaPlugins;

public class TranslationPresenter extends BasePresenter {

    private final String TAG = getClass().getSimpleName();

    private TranslationView view;
    private TranslationMapper translationMapper = new TranslationMapper();
    private Translation translation;

    //  мапа для языков en→Английски
    Map<String, String> languagesMap = new HashMap<>();

    // список направления переводов. Берётся из сети. Перенос в модель?
    private List<String> directions = new ArrayList<>();

    @Inject
    public TranslationPresenter() {
    }

    public TranslationPresenter(TranslationView view) {
        super();
        this.view = view;
    }

    public void onGetTranslation() {
        String text = view.getTextFromEditText();
        if (TextUtils.isEmpty(text) || text.isEmpty()) {
            view.showError("Введите текст для перевода.");
            view.showEmptyResut();
            return;
        }
        //TODO запилить DELAY!
        addSubscription(getSubscriptionForTranslated(text));
    }

    //TODO Написать тест. Смотри в закладках, там девчонка на конфе всё рассказала.
    private Subscription getSubscriptionForTranslated(String text) {
        //TODO комбинировать запрос в переводчик и в словарь.
        //https://habrahabr.ru/post/265997/
        //Observable.zip() - нужный мне приём в данном случае.
        //Объединяет в себе оба запроса и в переводчик и в словарь. ОЧЕНЬ КРУТО.

        return responseData.getTranslation(text, "ru-en")
                .map(translationMapper)
                .subscribe(new Observer<Translation>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "subscription: onCompleted()");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.w(TAG, "subscriprion: onError()");
                        RxJavaPlugins.getInstance().getErrorHandler().handleError(e);
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Translation response) {
                        if (null != response && !response.isEmpty()) {
                            translation = response;
                            view.showTranslation(translation);
                            Log.i(TAG, "response from server is OK");
                        } else {
                            view.showEmptyResut();
                            Log.w(TAG, "response from server is null or empty");
                        }
                    }
                });
    }
}
