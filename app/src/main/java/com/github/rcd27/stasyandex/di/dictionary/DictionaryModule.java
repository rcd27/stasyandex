package com.github.rcd27.stasyandex.di.dictionary;

import com.github.rcd27.stasyandex.Model;
import com.github.rcd27.stasyandex.di.ApplicationScope;
import com.github.rcd27.stasyandex.dictionary.DictionaryPresenter;
import com.github.rcd27.stasyandex.dictionary.DictionaryView;

import dagger.Module;
import dagger.Provides;

@Module
public class DictionaryModule {

    private DictionaryView view;

    public DictionaryModule(DictionaryView view) {
        this.view = view;
    }

    @Provides
    @ApplicationScope
    DictionaryPresenter dictionaryPresenter(Model model) {
        return new DictionaryPresenter(view, model);
    }
}
