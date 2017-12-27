package com.github.rcd27.stasyandex.di;

import com.github.rcd27.stasyandex.ModelImpl;
import com.github.rcd27.stasyandex.di.dictionary.DictionaryComponent;
import com.github.rcd27.stasyandex.di.dictionary.DictionaryModule;
import com.github.rcd27.stasyandex.di.translation.TranslationComponent;
import com.github.rcd27.stasyandex.di.translation.TranslationModule;

import dagger.Component;

@ApplicationScope
@Component(modules = ModelModule.class)
public interface AppComponent {

    TranslationComponent plus(TranslationModule translationModule);

    DictionaryComponent plus(DictionaryModule dictionaryModule);
}
