package com.github.rcd27.stasyandex.di;

import com.github.rcd27.stasyandex.api.ApiModule;
import com.github.rcd27.stasyandex.di.dictionary.DictionaryComponent;
import com.github.rcd27.stasyandex.di.dictionary.DictionaryModule;
import com.github.rcd27.stasyandex.di.translation.TranslationComponent;
import com.github.rcd27.stasyandex.di.translation.TranslationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApiModule.class
})

public interface AppComponent {

    TranslationComponent plus(TranslationModule translationModule);

    DictionaryComponent plus(DictionaryModule dictionaryModule);
}
