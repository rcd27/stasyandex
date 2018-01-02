package com.github.rcd27.stasyandex.di;

import com.github.rcd27.stasyandex.Model;
import com.github.rcd27.stasyandex.ModelImpl;
import com.github.rcd27.stasyandex.dictionary.DictionaryContract;
import com.github.rcd27.stasyandex.net.ApiModule;
import com.github.rcd27.stasyandex.translation.TranslationContract;

import dagger.Module;
import dagger.Provides;

@Module
public class ModelModule {

    @Provides
    Model provideMode(TranslationContract.Api translationAPI, DictionaryContract.Api dictionaryAPI) {
        return new ModelImpl(translationAPI, dictionaryAPI);
    }

    @Provides
    TranslationContract.Api translationAPI() {
        //TODO сюда надо передать Context приложения, чтобы добавить кэширование хттпклиента
        return ApiModule.getTranslationApi();
    }

    @Provides
    DictionaryContract.Api dictionaryAPI() {
        return ApiModule.getDictionaryApi();
    }
}

