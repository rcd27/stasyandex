package com.github.rcd27.stasyandex.di.dictionary;

import com.github.rcd27.stasyandex.dictionary.DictionaryContract;
import com.github.rcd27.stasyandex.dictionary.DictionaryPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class DictionaryModule {

    private final DictionaryContract.View view;

    public DictionaryModule(DictionaryContract.View view) {
        this.view = view;
    }

    @Provides
    DictionaryContract.Presenter dictionaryPresenter(DictionaryContract.Api api) {
        return new DictionaryPresenter(view, api);
    }
}
