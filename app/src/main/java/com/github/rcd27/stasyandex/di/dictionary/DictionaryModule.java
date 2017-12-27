package com.github.rcd27.stasyandex.di.dictionary;

import dagger.Module;
import dagger.Provides;

import com.github.rcd27.stasyandex.di.ApplicationScope;
import com.github.rcd27.stasyandex.dictionary.DictionaryPresenter;
import com.github.rcd27.stasyandex.dictionary.DictionaryView;

@Module
public class DictionaryModule {

    private DictionaryView view;

    public DictionaryModule(DictionaryView view) {
        this.view = view;
    }

    @Provides
    @ApplicationScope
    DictionaryPresenter dictionaryPresenter() {
        return new DictionaryPresenter(view);
    }
}
