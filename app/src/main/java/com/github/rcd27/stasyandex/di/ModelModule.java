package com.github.rcd27.stasyandex.di;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import com.github.rcd27.stasyandex.model.Const;
import com.github.rcd27.stasyandex.model.ApiModule;
import com.github.rcd27.stasyandex.model.dictionary.DictionaryAPI;
import com.github.rcd27.stasyandex.model.translation.TranslationAPI;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@Module
public class ModelModule {

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

    @Provides
    @ApplicationScope
    @Named(Const.UI_THREAD)
    Scheduler schedulerUI() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @ApplicationScope
    @Named(Const.IO_THREAD)
    Scheduler schedulerIO() {
        return Schedulers.io();
    }
}

