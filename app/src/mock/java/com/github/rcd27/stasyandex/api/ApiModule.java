package com.github.rcd27.stasyandex.api;

import com.github.rcd27.stasyandex.model.data.dictionary.*;
import com.github.rcd27.stasyandex.model.data.translation.*;
import com.github.rcd27.stasyandex.presentation.dictionary.*;
import com.github.rcd27.stasyandex.presentation.translation.*;

import java.util.*;

import javax.inject.*;

import dagger.*;
import io.reactivex.*;

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
    return (languageDirection, text, inLanguage) -> {
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
    };
  }
}
