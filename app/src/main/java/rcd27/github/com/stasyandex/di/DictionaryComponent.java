package rcd27.github.com.stasyandex.di;

import dagger.Component;
import rcd27.github.com.stasyandex.view.dictionary.DictionaryFragment;

@ApplicationScope
@Component(modules = DictionaryModule.class)
public interface DictionaryComponent {

    void inject(DictionaryFragment dictionaryFragment);

}
