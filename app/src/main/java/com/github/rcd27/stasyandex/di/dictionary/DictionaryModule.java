package com.github.rcd27.stasyandex.di.dictionary;

import com.github.rcd27.stasyandex.presentation.dictionary.*;

import dagger.*;

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
