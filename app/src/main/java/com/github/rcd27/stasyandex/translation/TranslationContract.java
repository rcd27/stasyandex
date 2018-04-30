package com.github.rcd27.stasyandex.translation;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.github.rcd27.stasyandex.common.BaseView;
import com.github.rcd27.stasyandex.data.translation.AvailableLanguages;
import com.github.rcd27.stasyandex.data.translation.ProbableLanguage;
import com.github.rcd27.stasyandex.data.translation.Translation;

import retrofit2.http.GET;
import retrofit2.http.Query;

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
        io.reactivex.Single<AvailableLanguages> getAvailableLangs(@Query("ui") String forLanguage);

        @GET("api/v1.5/tr.json/detect")
        io.reactivex.Single<ProbableLanguage> getProbableLanguage(@Query("text") String text);

        @GET("api/v1.5/tr.json/translate")
        io.reactivex.Single<Translation> getTranslation(@Query("text") String textToTranslate,
                                           @Query("lang") String translationDirection);
    }
}
