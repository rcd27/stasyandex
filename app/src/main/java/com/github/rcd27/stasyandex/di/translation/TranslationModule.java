package com.github.rcd27.stasyandex.di.translation;


import android.content.Context;

import com.github.rcd27.stasyandex.Model;
import com.github.rcd27.stasyandex.translation.TranslationContract;
import com.github.rcd27.stasyandex.translation.TranslationFragment;
import com.github.rcd27.stasyandex.translation.TranslationPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class TranslationModule {

    private final TranslationContract.View view;
    private final Context context;

    public TranslationModule(TranslationContract.View view,
                             TranslationFragment.TranslateButtonListener li,
                             Context context) {

        this.view = view;
        TranslationFragment.TranslateButtonListener listener = li;
        this.context = context;
    }

    @Provides
    TranslationContract.Presenter translationPresenter(Model model) {
        return new TranslationPresenter(view, context, model);
    }
}
