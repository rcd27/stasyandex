package rcd27.github.com.stasyandex.presenter.translation;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

import javax.inject.Inject;

import rcd27.github.com.stasyandex.TextUtil;
import rcd27.github.com.stasyandex.model.Const;
import rcd27.github.com.stasyandex.model.translation.dto.Translation;
import rcd27.github.com.stasyandex.presenter.BasePresenter;
import rcd27.github.com.stasyandex.view.history.HistoryItem;
import rcd27.github.com.stasyandex.view.translation.TranslationView;
import retrofit2.Retrofit;
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
        languagesMap = Translation.createLanguagesMap();
    }

    public void onGetTranslation() {
        String text = view.getTextFromEditText();
        if (TextUtils.isEmpty(text) || text.isEmpty()) {
            view.showError("Введите текст для перевода.");
            view.showEmptyResult();
            return;
        }
        addSubscription(getSubscriptionForTranslated(text));
    }

    private Subscription getSubscriptionForTranslated(String text) {
        return responseData.getTranslation(text, getDirection())
                .doOnError(throwable -> {
                    Log.w(TAG, "Retrofit/rxJava error!");
                })
                .doOnNext(response -> {
                    if (null != response && !response.isEmpty()) {
                        translation = response;
                        view.showTranslation(translation);
                        //TODO сделать задержку
                        saveToHistory(translation);
                        Log.i(TAG, "response from server is OK");
                    } else {
                        view.showEmptyResult();
                        Log.w(TAG, "response from server is null or empty");
                    }
                })
                .subscribe();
    }

    @NonNull
    public String getDirection() {
        String languageFrom = view.getLanguageFrom();
        String languageFromAbbr = TextUtil.findKeyByValue(languagesMap, languageFrom);

        String languageTo = view.getLanguageTo();
        String languageToAbbr = TextUtil.findKeyByValue(languagesMap, languageTo);

        Log.w(TAG, "Direction is" + languageFromAbbr.concat("-").concat(languageToAbbr));

        return languageFromAbbr.concat("-").concat(languageToAbbr);
    }

    //TODO FIXME инт пробрасывается в презентер и обратно. Убрать, чтобы вью ничего не знал.
    public void onChooseLanguage(int directionInt) {
        view.chooseLanguage(directionInt);
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

    private void saveToHistory(Translation toSave) {
        String to = TextUtil.commaRawFromList(translation.getTranslationResult());
        String from = view.getTextFromEditText();

        HistoryItem historyItem = new HistoryItem(translation.getDirection(), to, from, 0, true);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(historyItem);

        SharedPreferences prefs = context.getSharedPreferences(Const.HISTORY_CACHE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString(toSave.getDirection() + toSave.getTranslationResult().toString(), json);
        editor.apply();
    }
}
