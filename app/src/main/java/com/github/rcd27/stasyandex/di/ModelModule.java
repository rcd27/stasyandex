package com.github.rcd27.stasyandex.di;

import com.github.rcd27.stasyandex.Model;
import com.github.rcd27.stasyandex.ModelImpl;
import com.github.rcd27.stasyandex.dictionary.DictionaryAPI;
import com.github.rcd27.stasyandex.net.ApiModule;
import com.github.rcd27.stasyandex.translation.TranslationAPI;

import dagger.Module;
import dagger.Provides;

@Module
public class ModelModule {

    @Provides
    @ApplicationScope
    Model provideMode(TranslationAPI translationAPI, DictionaryAPI dictionaryAPI) {
        return new ModelImpl(translationAPI, dictionaryAPI);
    }

    @Provides
    @ApplicationScope
    TranslationAPI translationAPI() {
        //TODO сюда надо передать Context приложения, чтобы добавить кэширование хттпклиента
        return ApiModule.getTranslationApi();
    }

    @Provides
    @ApplicationScope
    DictionaryAPI dictionaryAPI() {
        return ApiModule.getDictionaryApi();
    }
}

