package rcd27.github.com.stasyandex.di;

import dagger.Module;
import dagger.Provides;
import rcd27.github.com.stasyandex.presenter.dictionary.DictionaryPresenter;
import rcd27.github.com.stasyandex.view.dictionary.DictionaryView;

@Module
public class DictionaryModule {

    private DictionaryView view;

    public DictionaryModule(DictionaryView view) {
        this.view = view;
    }

    @Provides
    @ApplicationScope
    DictionaryPresenter dictionaryPresenter() {
        return new DictionaryPresenter(view);
    }
}
