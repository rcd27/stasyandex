package com.github.rcd27.stasyandex.translation;


import android.annotation.*;
import android.content.*;
import android.support.annotation.*;
import android.text.*;

import com.github.rcd27.stasyandex.common.*;
import com.github.rcd27.stasyandex.data.history.*;
import com.github.rcd27.stasyandex.data.translation.*;
import com.google.gson.*;

import java.util.*;

import io.reactivex.android.schedulers.*;
import io.reactivex.disposables.*;
import io.reactivex.schedulers.*;
import timber.log.*;

public class TranslationPresenter extends BasePresenter implements TranslationContract.Presenter {
  private final String tag = getClass().getSimpleName();

  private final TranslationContract.View view;
  private final Context context;
  private final TranslationContract.Api api;

  //TODO получать из Translate API при загрузке приложения в первый раз, сохранять в Preferences.
  private final Map<String, String> languagesMap;

  public TranslationPresenter(TranslationContract.View view,
                              Context context,
                              TranslationContract.Api api) {

    this.view = view;
    this.context = context;
    this.api = api;
    this.languagesMap = Translation.createLanguagesMap();
  }

  @Override
  public void getTranslationForTextFromEditText() {
    String text = view.getTextFromEditText();
    if (TextUtils.isEmpty(text)) {
      view.showEmpty();
      return;
    }
    addDisposable(translate(text));
  }

  // TODO: прикрутить задержку, чтобы экшон не происходил по каждому нажатию
  @SuppressLint("RxSubscribeOnError")
  @NonNull
  private Disposable translate(@NonNull String text) {
    return api.getTranslation(text, getDirection())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnError(throwable -> Timber.tag(tag).w("Retrofit/rxJava error!"))
        .subscribe(response -> {
          if (null != response && !response.isEmpty()) {
            view.showTranslation(response);
            saveToHistory(response);
          } else {
            view.showEmpty();
            Timber.tag(tag).w("response from server is null or empty");
          }
        });
  }

  @NonNull
  @Override
  public String getDirection() {
    //FIXME: не должно это из view доставаться
    String languageFrom = view.getLanguageFrom();
    String languageFromAbbr = TextUtil.findKeyByValue(languagesMap, languageFrom);

    String languageTo = view.getLanguageTo();
    String languageToAbbr = TextUtil.findKeyByValue(languagesMap, languageTo);

    Timber.tag(tag).i("Direction is %s",
        languageFromAbbr.concat("-").concat(languageToAbbr));

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

    // FIXME: move that to Interactor
    SharedPreferences prefs = context.getSharedPreferences(Const.HISTORY_CACHE, Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = prefs.edit();

    editor.putString(current.getDirection() + current.getTranslationResult().toString(), json);
    editor.apply();
  }
}
