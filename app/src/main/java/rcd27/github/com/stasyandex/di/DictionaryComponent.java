package rcd27.github.com.stasyandex.di;

import dagger.Component;
import rcd27.github.com.stasyandex.view.dictionary.DictionaryFragment;

//TODO залить всё в Appcomponent: этот компонент и переводчика.
@ApplicationScope
@Component(modules = DictionaryModule.class)
public interface DictionaryComponent {

    void inject(DictionaryFragment dictionaryFragment);

}
