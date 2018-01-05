package com.github.rcd27.stasyandex.api;

import com.github.rcd27.stasyandex.data.dictionary.Definition;
import com.github.rcd27.stasyandex.data.dictionary.DicResult;
import com.github.rcd27.stasyandex.data.dictionary.DicTranslation;
import com.github.rcd27.stasyandex.data.translation.AvailableLanguages;
import com.github.rcd27.stasyandex.data.translation.ProbableLanguage;
import com.github.rcd27.stasyandex.data.translation.Translation;
import com.github.rcd27.stasyandex.dictionary.DictionaryContract;
import com.github.rcd27.stasyandex.translation.TranslationContract;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Single;

@Module
public class ApiModule {

    @Provides
    @Singleton
    TranslationContract.Api provideMockTranslationApi() {
        return new TranslationContract.Api() {

            @Override
            public Single<AvailableLanguages> getAvailableLangs(String forLanguage) {
                return Single.just(new AvailableLanguages());
            }

            @Override
            public Single<ProbableLanguage> getProbableLanguage(String text) {
                return Single.just(new ProbableLanguage());
            }

            @Override
            public Single<Translation> getTranslation(String textToTranslate, String translationDirection) {
                Translation fakeTranslation = new Translation();
                fakeTranslation.setDirection(translationDirection);
                List<String> fakeTranslationResult = new ArrayList<>();
                fakeTranslationResult.add("Моковый перевод");
                fakeTranslation.setTranslationResult(fakeTranslationResult);
                return Single.just(fakeTranslation);
            }
        };
    }

    @Provides
    @Singleton
    DictionaryContract.Api provideMockDictionaryApi() {
        return new DictionaryContract.Api() {
            @Override
            public Single<DicResult> getDicResultFor(String languageDirection, String text, String inLanguage) {
                DicResult fakeDicResult = new DicResult();
                List<Definition> fakeDefinitions = new ArrayList<>();
                Definition fakeDef = new Definition();
                fakeDef.setText("Синоним №1");
                fakeDef.setPos("сущ.");
                fakeDef.setAnm("?");
                List<DicTranslation> fakeDicTrans = new ArrayList<>();
                DicTranslation dt = new DicTranslation();
                dt.setText("ёбушки");
                fakeDef.setDicTranslation(fakeDicTrans);
                fakeDefinitions.add(fakeDef);
                fakeDicResult.setDefinitionList(fakeDefinitions);

                return Single.just(fakeDicResult);
            }
        };
    }
}
