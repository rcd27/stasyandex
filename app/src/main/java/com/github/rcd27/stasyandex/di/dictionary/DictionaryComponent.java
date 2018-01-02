package com.github.rcd27.stasyandex.di.dictionary;

import com.github.rcd27.stasyandex.dictionary.DictionaryFragment;

import dagger.Subcomponent;

@Subcomponent(modules = DictionaryModule.class)
public interface DictionaryComponent {

    void inject(DictionaryFragment dictionaryFragment);
}
