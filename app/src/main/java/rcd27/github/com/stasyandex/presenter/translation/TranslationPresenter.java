package rcd27.github.com.stasyandex.presenter.translation;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import rcd27.github.com.stasyandex.TextUtil;
import rcd27.github.com.stasyandex.model.Const;
import rcd27.github.com.stasyandex.model.translation.dto.Translation;
import rcd27.github.com.stasyandex.presenter.BasePresenter;
import rcd27.github.com.stasyandex.view.translation.TranslationView;
import rx.Subscription;

public class TranslationPresenter extends BasePresenter {

    private final String TAG = getClass().getSimpleName();

    private TranslationView view;

    private Context context;

    private Translation translation;

    private Map<String, String> languagesMap;

    @Inject
    public TranslationPresenter() {
    }

    public TranslationPresenter(TranslationView view, Context context) {
        super();
        this.view = view;
        this.context = context;
        //TODO сделать так, чтобы только один раз подгружалось из сети.
        addSubscription(getSubscriptionForAvailableLanguages("ru"));
    }

    public void onGetTranslation() {
        String text = view.getTextFromEditText();
        if (TextUtils.isEmpty(text) || text.isEmpty()) {
            view.showError("Введите текст для перевода.");
            view.showEmptyResut();
            return;
        }
        addSubscription(getSubscriptionForTranslated(text));
    }

    private Subscription getSubscriptionForTranslated(String text) {
        //TODO мб вшить direction поглубже?
        String languageFrom = view.getLanguageFrom();
        String languageFromAbbr = TextUtil.findKeyByValue(languagesMap,languageFrom);

        String languageTo = view.getLanguageTo();
        String languageToAbbr = TextUtil.findKeyByValue(languagesMap, languageTo);

        String direction = languageFromAbbr.concat("-").concat(languageToAbbr);

        return responseData.getTranslation(text, direction)
                .doOnNext(response -> {
                    if (null != response && !response.isEmpty()) {
                        translation = response;
                        view.showTranslation(translation);
                        Log.i(TAG, "response from server is OK");
                    } else {
                        view.showEmptyResut();
                        Log.w(TAG, "response from server is null or empty");
                    }
                })
                .subscribe();
    }

    //TODO FIXME инт пробрасывается в презентер и обратно. Убрать, чтобы вью ничего не знал.
    public void onChooseLanguage(int directionInt) {
        view.chooseLanguage(directionInt);
    }

    private Subscription getSubscriptionForAvailableLanguages(String forLanguage) {
        return responseData.getAvailableLanguages(forLanguage)
                .doOnNext(response -> {
                    languagesMap = response.getLanguages();
                    SharedPreferences prefs = context
                            .getSharedPreferences(Const.TRANSLATION_CACHE, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    for (Map.Entry<String,String> entry : languagesMap.entrySet()) {
                        editor.putString(entry.getKey(), entry.getValue());
                    }
                    editor.apply();
                    Log.w(TAG, "Updated Available Languages!");
                })
                .subscribe();
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

    public void switchToHistory() {
        view.openHistory();
    }
}
