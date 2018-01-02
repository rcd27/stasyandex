package com.github.rcd27.stasyandex.translation;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.github.rcd27.stasyandex.Model;
import com.github.rcd27.stasyandex.common.BasePresenter;
import com.github.rcd27.stasyandex.common.Const;
import com.github.rcd27.stasyandex.common.TextUtil;
import com.github.rcd27.stasyandex.data.translation.Translation;
import com.github.rcd27.stasyandex.data.history.HistoryItem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

import rx.Subscription;

public class TranslationPresenter extends BasePresenter implements TranslationContract.Presenter {
    private final String TAG = getClass().getSimpleName();

    private final TranslationContract.View view;
    private final Context context;
    private final Model model;

    //TODO получать из Translate API при загрузке приложения в первый раз, сохранять в Preferences.
    private final Map<String, String> languagesMap;

    public TranslationPresenter(TranslationContract.View view,
                                Context context,
                                Model model) {

        this.view = view;
        this.context = context;
        this.model = model;
        this.languagesMap = Translation.createLanguagesMap();
    }

    @Override
    public void getTranslationForTextFromEditText() {
        String text = view.getTextFromEditText();
        if (TextUtils.isEmpty(text) || text.isEmpty()) {
            view.showError("Введите текст для перевода.");
            view.showEmpty();
            return;
        }
        addSubscription(getSubscriptionForTranslated(text));
    }

    // TODO: прикрутить задержку, чтобы экшон не происходил по каждому нажатию
    @NonNull
    private Subscription getSubscriptionForTranslated(@NonNull String text) {
        return model.getTranslation(text, getDirection())
                .doOnError(throwable -> Log.w(TAG, "Retrofit/rxJava error!"))
                .subscribe(response -> {
                    if (null != response && !response.isEmpty()) {
                        view.showTranslation(response);
                        saveToHistory(response);
//                        view.showError("«Переведено сервисом «Яндекс.Переводчик»");
                        Log.i(TAG, "response from server is OK");
                    } else {
                        view.showEmpty();
                        Log.w(TAG, "response from server is null or empty");
                    }
                });
    }

    @NonNull
    @Override
    public String getDirection() {
        String languageFrom = view.getLanguageFrom();
        String languageFromAbbr = TextUtil.findKeyByValue(languagesMap, languageFrom);

        String languageTo = view.getLanguageTo();
        String languageToAbbr = TextUtil.findKeyByValue(languagesMap, languageTo);

        Log.w(TAG, "Direction is" + languageFromAbbr.concat("-").concat(languageToAbbr));

        return languageFromAbbr.concat("-").concat(languageToAbbr);
    }

    //TODO FIXME инт пробрасывается в презентер и обратно. Убрать, чтобы вью ничего не знал.
    @Override
    public void onChooseLanguage(int directionInt) {
        view.chooseLanguage(directionInt);
    }

    @Override
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
                    default:
                        view.showError("Ошибка выбора языка");
                }
            }
        }
    }

    @Override
    public void clearTranslationEditText() {
        view.clearEditText();
    }

    @Override
    public void switchToHistory() {
        view.openHistory();
    }

    private void saveToHistory(Translation current) {
        String to = TextUtil.commaRawFromList(current.getTranslationResult());
        String from = view.getTextFromEditText();

        HistoryItem historyItem = new HistoryItem(current.getDirection(), to, from, 0, true);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(historyItem);

        SharedPreferences prefs = context.getSharedPreferences(Const.HISTORY_CACHE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString(current.getDirection() + current.getTranslationResult().toString(), json);
        editor.apply();
    }
}
