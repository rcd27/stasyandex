package com.github.rcd27.stasyandex.presentation.translation;


import android.content.*;
import android.support.annotation.*;
import android.text.*;

import com.github.rcd27.airbag.*;
import com.github.rcd27.stasyandex.common.*;
import com.github.rcd27.stasyandex.model.business.translation.*;
import com.github.rcd27.stasyandex.model.data.translation.*;
import com.github.rcd27.stasyandex.presentation.*;

import java.util.*;

import io.reactivex.android.schedulers.*;
import io.reactivex.disposables.*;

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

    ApiRequest<Translation> request = interactor.getTranslation(text, getDirection());
    Disposable errors = request.errors
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(e -> view.showError(e.getMessage()), throwable -> {
          // FIXME: this is still can be broken in Observer
        });

    Disposable translations = request.response
        .doOnEach(t -> onSaveToHistory(t.getValue()))
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(view::showTranslation);

    Disposable states = request.state
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(view::showState);

    addDisposable(request.execute(errors, translations, states));
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
