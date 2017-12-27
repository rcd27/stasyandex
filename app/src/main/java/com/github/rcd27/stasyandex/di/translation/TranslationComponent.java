package com.github.rcd27.stasyandex.di.translation;

import dagger.Component;

import com.github.rcd27.stasyandex.di.ApplicationScope;
import com.github.rcd27.stasyandex.translation.TranslationFragment;

@ApplicationScope
@Component(modules = {TranslationModule.class})
public interface TranslationComponent {

    void inject(TranslationFragment translationFragment);

}
