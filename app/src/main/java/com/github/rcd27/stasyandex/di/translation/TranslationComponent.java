package com.github.rcd27.stasyandex.di.translation;

import com.github.rcd27.stasyandex.di.ApplicationScope;
import com.github.rcd27.stasyandex.translation.TranslationFragment;

import dagger.Subcomponent;

@ApplicationScope
@Subcomponent(modules = {TranslationModule.class})
public interface TranslationComponent {

    void inject(TranslationFragment translationFragment);

}
