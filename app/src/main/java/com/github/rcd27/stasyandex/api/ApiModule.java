package com.github.rcd27.stasyandex.api;

import com.github.rcd27.stasyandex.dictionary.DictionaryContract;
import com.github.rcd27.stasyandex.network.RetrofitApiFactory;
import com.github.rcd27.stasyandex.translation.TranslationContract;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApiModule {

    @Provides
    @Singleton
    TranslationContract.Api provideTranslationApi() {
        return RetrofitApiFactory.getTranslationApi();
    }

    @Provides
    @Singleton
    DictionaryContract.Api provideDictionaryApi() {
        return RetrofitApiFactory.getDictionaryApi();
    }
}
