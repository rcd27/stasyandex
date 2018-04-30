package com.github.rcd27.stasyandex.presentation.translation;

import android.content.*;
import android.support.annotation.*;

import com.github.rcd27.stasyandex.data.translation.*;
import com.github.rcd27.stasyandex.presentation.*;

import io.reactivex.*;
import retrofit2.http.*;

public interface TranslationContract {

  interface View extends BaseView {

    String getTextFromEditText();

    void showTranslation(Translation translation);

    void chooseLanguage(final int direction);

    void showLanguageFrom(String selectedLanguage);

    void showLanguageTo(String selectedLanguage);

    String getLanguageFrom();

    String getLanguageTo();

    void openHistory();

    void clearEditText();
  }

  interface Presenter {

    void getTranslationForTextFromEditText();

    @NonNull
    String getDirection();

    void onChooseLanguage(int directionInt);

    void handleIntentForSelectedLanguages(Intent intent);

    void clearTranslationEditText();

    void switchToHistory();
  }

  interface Api {
    @GET("api/v1.5/tr.json/getLangs")
    Single<AvailableLanguages> getAvailableLangs(@Query("ui") String forLanguage);

    @GET("api/v1.5/tr.json/detect")
    Single<ProbableLanguage> getProbableLanguage(@Query("text") String text);

    @GET("api/v1.5/tr.json/translate")
    Single<Translation> getTranslation(@Query("text") String textToTranslate,
                                       @Query("lang") String translationDirection);
  }
}
