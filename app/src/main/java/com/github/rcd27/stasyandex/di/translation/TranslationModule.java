package com.github.rcd27.stasyandex.di.translation;


import android.content.Context;

import dagger.Module;
import dagger.Provides;

import com.github.rcd27.stasyandex.di.ApplicationScope;
import com.github.rcd27.stasyandex.translation.TranslationPresenter;
import com.github.rcd27.stasyandex.translation.TranslationFragment;
import com.github.rcd27.stasyandex.translation.TranslationView;

@Module
public class TranslationModule {

    private TranslationView view;
    private TranslationFragment.TranslateButtonListener listener;
    private Context context;

    public TranslationModule(TranslationView view, TranslationFragment.TranslateButtonListener li,
                             Context context) {
        this.view = view;
        this.listener = li;
        this.context = context;
    }

    @Provides
    @ApplicationScope
    TranslationPresenter translationPresenter() {
        return new TranslationPresenter(view,context);
    }
}