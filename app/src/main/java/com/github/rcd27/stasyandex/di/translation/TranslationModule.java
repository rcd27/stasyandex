package com.github.rcd27.stasyandex.di.translation;


import android.content.Context;

import com.github.rcd27.stasyandex.Model;
import com.github.rcd27.stasyandex.di.ApplicationScope;
import com.github.rcd27.stasyandex.translation.TranslationFragment;
import com.github.rcd27.stasyandex.translation.TranslationPresenter;
import com.github.rcd27.stasyandex.translation.TranslationView;

import dagger.Module;
import dagger.Provides;

@Module
public class TranslationModule {

    private TranslationView view;
    private TranslationFragment.TranslateButtonListener listener;
    private Context context;

    public TranslationModule(TranslationView view,
                             TranslationFragment.TranslateButtonListener li,
                             Context context) {

        this.view = view;
        this.listener = li;
        this.context = context;
    }

    @Provides
    @ApplicationScope
    TranslationPresenter translationPresenter(Model model) {
        return new TranslationPresenter(view, context, model);
    }
}
