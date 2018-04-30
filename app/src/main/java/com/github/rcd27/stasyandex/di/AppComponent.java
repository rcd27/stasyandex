package com.github.rcd27.stasyandex.di;

import com.github.rcd27.stasyandex.api.*;
import com.github.rcd27.stasyandex.di.dictionary.*;
import com.github.rcd27.stasyandex.di.translation.*;

import javax.inject.*;

import dagger.*;

@Singleton
@Component(modules = {
    ApiModule.class
})

public interface AppComponent {

  TranslationComponent plus(TranslationModule translationModule);

  DictionaryComponent plus(DictionaryModule dictionaryModule);
}
