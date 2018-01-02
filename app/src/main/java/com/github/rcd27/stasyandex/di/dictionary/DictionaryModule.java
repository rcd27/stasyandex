package com.github.rcd27.stasyandex.di.dictionary;

import com.github.rcd27.stasyandex.Model;
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
    DictionaryContract.Presenter dictionaryPresenter(Model model) {
        return new DictionaryPresenter(view, model);
    }
}
