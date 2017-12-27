package com.github.rcd27.stasyandex.di;

import dagger.Module;
import dagger.Provides;
import com.github.rcd27.stasyandex.presenter.dictionary.DictionaryPresenter;
import com.github.rcd27.stasyandex.view.dictionary.DictionaryView;

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
