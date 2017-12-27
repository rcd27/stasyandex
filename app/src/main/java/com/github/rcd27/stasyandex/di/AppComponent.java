package com.github.rcd27.stasyandex.di;

import dagger.Component;
import com.github.rcd27.stasyandex.model.ModelImpl;
import com.github.rcd27.stasyandex.presenter.BasePresenter;
import com.github.rcd27.stasyandex.presenter.dictionary.DictionaryPresenter;
import com.github.rcd27.stasyandex.presenter.translation.TranslationPresenter;

@ApplicationScope
@Component(modules = {ModelModule.class, PresenterModule.class})
public interface AppComponent {

    void inject(ModelImpl dataFromApi);

    void inject(BasePresenter basePresenter);

    void inject(TranslationPresenter translationPresenter);

    void inject(DictionaryPresenter dictionaryPresenter);

}
