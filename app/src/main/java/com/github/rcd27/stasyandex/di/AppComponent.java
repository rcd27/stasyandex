package com.github.rcd27.stasyandex.di;

import com.github.rcd27.stasyandex.di.dictionary.DictionaryComponent;
import com.github.rcd27.stasyandex.di.dictionary.DictionaryModule;
import com.github.rcd27.stasyandex.di.translation.TranslationComponent;
import com.github.rcd27.stasyandex.di.translation.TranslationModule;

import dagger.Component;

@Component(modules = ModelModule.class)
public interface AppComponent {

    TranslationComponent plus(TranslationModule translationModule);

    DictionaryComponent plus(DictionaryModule dictionaryModule);
}
