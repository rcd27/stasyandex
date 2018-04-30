package com.github.rcd27.stasyandex.di.translation;


import android.content.*;

import com.github.rcd27.stasyandex.presentation.translation.*;

import dagger.*;

@Module
public class TranslationModule {

  private final TranslationContract.View view;
  private final Context context;

  public TranslationModule(TranslationContract.View view,
                           Context context) {

    this.view = view;
    this.context = context;
  }

  @Provides
  TranslationContract.Presenter translationPresenter(TranslationContract.Api api) {
    return new TranslationPresenter(view, context, api);
  }
}
