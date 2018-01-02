package com.github.rcd27.stasyandex.di.translation;

import com.github.rcd27.stasyandex.translation.TranslationFragment;

import dagger.Subcomponent;

@Subcomponent(modules = {TranslationModule.class})
public interface TranslationComponent {

    void inject(TranslationFragment translationFragment);
}
