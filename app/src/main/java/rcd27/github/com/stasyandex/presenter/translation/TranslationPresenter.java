package rcd27.github.com.stasyandex.presenter.translation;


import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import rcd27.github.com.stasyandex.model.Const;
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

    //  мапа для языков en→Английски. Берётся из сети / с базы.
    private Map<String, String> languagesMap = new HashMap<>();

    // список направления переводов. Берётся из сети. Перенос в модель? Типа "ru-en"
    private List<String> directions = new ArrayList<>();

    @Inject
    public TranslationPresenter() {
    }

    public TranslationPresenter(TranslationView view) {
        super();
        this.view = view;
        languagesMap.put("ru", "Русский");
        languagesMap.put("en", "Английский");
        languagesMap.put("az", "Азербайджанский");
        languagesMap.put("se", "Сербский");
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

    //TODO прикручивать направление перевода начну отсюда пожалуй
    private Subscription getSubscriptionForTranslated(String text) {
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

    public void onChooseLanguage(int direction) {
        view.chooseLanguage(new ArrayList<>(languagesMap.values()), direction);
        Log.w(TAG, languagesMap.values().toString());
    }

    public void handleIntentForSelectedLanguages(Intent intent) {
        if (intent.hasExtra("direction") && intent.hasExtra("selectedLanguage")) {
            int direction = intent.getIntExtra("direction", 0);
            String selectedLanguage = intent.getStringExtra("selectedLanguage");

            if (languagesMap.containsValue(selectedLanguage)) {
                switch (direction) {
                    case Const.DIRECTION_FROM:
                        view.showLanguageFrom(selectedLanguage);
                        break;
                    case Const.DIRECTION_TO:
                        view.showLanguageTo(selectedLanguage);
                        break;
                }
            }
        }

    }
}
