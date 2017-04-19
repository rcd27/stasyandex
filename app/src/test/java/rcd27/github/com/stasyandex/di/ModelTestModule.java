package rcd27.github.com.stasyandex.di;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import rcd27.github.com.stasyandex.model.Const;
import rcd27.github.com.stasyandex.model.dictionary.DictionaryAPI;
import rcd27.github.com.stasyandex.model.translation.TranslationAPI;
import rx.Scheduler;
import rx.schedulers.Schedulers;

import static org.mockito.Mockito.mock;

@Module
public class ModelTestModule {

    @Provides
    @ApplicationScope
    TranslationAPI translationAPI() {
        return mock(TranslationAPI.class);
    }

    @Provides
    @ApplicationScope
    DictionaryAPI dictionaryAPI() {
        return mock(DictionaryAPI.class);
    }

    @Provides
    @ApplicationScope
    @Named(Const.UI_THREAD)
    Scheduler shedulerUI() {
        return Schedulers.immediate();
    }

    @Provides
    @ApplicationScope
    @Named(Const.IO_THREAD)
    Scheduler schedulerIO() {
        return Schedulers.immediate();
    }
}
