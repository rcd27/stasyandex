package com.github.rcd27.stasyandex.di.dictionary;

import com.github.rcd27.stasyandex.presentation.dictionary.*;

import dagger.*;

@Subcomponent(modules = DictionaryModule.class)
public interface DictionaryComponent {

  void inject(DictionaryFragment dictionaryFragment);
}
