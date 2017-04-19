package rcd27.github.com.stasyandex.di;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import rcd27.github.com.stasyandex.Const;
import rcd27.github.com.stasyandex.model.ApiModule;
import rcd27.github.com.stasyandex.model.dictionary.DictionaryAPI;
import rcd27.github.com.stasyandex.model.translation.TranslationAPI;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@Module
public class ModelModule {

    @Provides
    @ApplicationScope
    TranslationAPI translationAPI() {
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

