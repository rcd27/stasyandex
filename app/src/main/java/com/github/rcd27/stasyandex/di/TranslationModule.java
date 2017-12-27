package com.github.rcd27.stasyandex.di;


import android.content.Context;

import dagger.Module;
import dagger.Provides;
import com.github.rcd27.stasyandex.presenter.translation.TranslationPresenter;
import com.github.rcd27.stasyandex.view.translation.TranslationFragment;
import com.github.rcd27.stasyandex.view.translation.TranslationView;

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
