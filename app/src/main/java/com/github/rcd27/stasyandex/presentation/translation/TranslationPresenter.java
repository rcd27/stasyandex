package com.github.rcd27.stasyandex.presentation.translation;


import android.content.*;
import android.support.annotation.*;
import android.text.*;

import com.github.rcd27.stasyandex.common.*;
import com.github.rcd27.stasyandex.model.business.translation.*;
import com.github.rcd27.stasyandex.model.data.translation.*;
import com.github.rcd27.stasyandex.presentation.*;

import java.util.*;

import io.reactivex.android.schedulers.*;
import io.reactivex.disposables.*;
import timber.log.*;

public class TranslationPresenter extends BasePresenter implements TranslationContract.Presenter {
  private final String tag = getClass().getSimpleName();

  private final TranslationContract.View view;
  private final TranslationInteractor interactor;

  //TODO получать из Translate API при загрузке приложения в первый раз, сохранять в Preferences.
  private final Map<String, String> languagesMap;

  public TranslationPresenter(
      TranslationContract.View view,
      TranslationInteractor interactor) {

    this.view = view;
    this.interactor = interactor;
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
  // это просто сделать с помощью RxBindings в самой вьюхе, поэтому сейчас не заморачиваться.
  @NonNull
  private Disposable translate(@NonNull String text) {

    return interactor.getTranslation(text, getDirection())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(response -> {
          if (null != response && !response.isEmpty()) {
            view.showTranslation(response);
            onSaveToHistory(response);
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
    // TODO: interactor.getDirection()
    String languageFrom = view.getLanguageFrom();
    String languageFromAbbr = TextUtil.findKeyByValue(languagesMap, languageFrom);

    String languageTo = view.getLanguageTo();
    String languageToAbbr = TextUtil.findKeyByValue(languagesMap, languageTo);

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

  private void onSaveToHistory(Translation current) {
    interactor.saveCurrentTranslationToHistory(current, view.getTextFromEditText());
  }
}
