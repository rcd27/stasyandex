package rcd27.github.com.stasyandex.di;

import dagger.Component;
import rcd27.github.com.stasyandex.view.translation.TranslationFragment;

@ApplicationScope
@Component(modules = {TranslationModule.class})
public interface TranslationComponent {

    void inject(TranslationFragment translationFragment);

}
