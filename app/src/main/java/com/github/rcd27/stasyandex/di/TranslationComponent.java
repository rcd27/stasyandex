package com.github.rcd27.stasyandex.di;

import dagger.Component;
import com.github.rcd27.stasyandex.view.translation.TranslationFragment;

@ApplicationScope
@Component(modules = {TranslationModule.class})
public interface TranslationComponent {

    void inject(TranslationFragment translationFragment);

}
