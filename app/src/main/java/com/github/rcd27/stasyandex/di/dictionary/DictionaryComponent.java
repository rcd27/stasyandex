package com.github.rcd27.stasyandex.di.dictionary;

import dagger.Component;

import com.github.rcd27.stasyandex.di.ApplicationScope;
import com.github.rcd27.stasyandex.dictionary.DictionaryFragment;

//TODO залить всё в Appcomponent: этот компонент и переводчика.
@ApplicationScope
@Component(modules = DictionaryModule.class)
public interface DictionaryComponent {

    void inject(DictionaryFragment dictionaryFragment);

}
