package com.github.rcd27.stasyandex.di.translation;

import com.github.rcd27.stasyandex.presentation.translation.*;

import dagger.*;

@Subcomponent(modules = {TranslationModule.class})
public interface TranslationComponent {

  void inject(TranslationFragment translationFragment);
}
