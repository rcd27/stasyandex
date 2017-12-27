package com.github.rcd27.stasyandex.di;

import dagger.Component;
import com.github.rcd27.stasyandex.view.dictionary.DictionaryFragment;

//TODO залить всё в Appcomponent: этот компонент и переводчика.
@ApplicationScope
@Component(modules = DictionaryModule.class)
public interface DictionaryComponent {

    void inject(DictionaryFragment dictionaryFragment);

}
