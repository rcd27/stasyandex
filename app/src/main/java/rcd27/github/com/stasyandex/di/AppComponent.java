package rcd27.github.com.stasyandex.di;

import dagger.Component;
import rcd27.github.com.stasyandex.model.ModelImpl;
import rcd27.github.com.stasyandex.presenter.BasePresenter;
import rcd27.github.com.stasyandex.presenter.dictionary.DictionaryPresenter;
import rcd27.github.com.stasyandex.presenter.translation.TranslationPresenter;
import rcd27.github.com.stasyandex.view.dictionary.DictionaryFragment;
import rcd27.github.com.stasyandex.view.translation.TranslationFragment;

@ApplicationScope
@Component(modules = {ModelModule.class, PresenterModule.class, ViewModule.class})
public interface AppComponent {

    void inject(ModelImpl dataFromApi);

    void inject(BasePresenter basePresenter);

    void inject(TranslationPresenter translationPresenter);

    void inject(DictionaryPresenter dictionaryPresenter);

    void inject(TranslationFragment translationFragment);

    void inject(DictionaryFragment dictionaryFragment);
}
