package com.github.rcd27.stasyandex.di;

import com.github.rcd27.stasyandex.BasePresenter;
import com.github.rcd27.stasyandex.ModelImpl;
import com.github.rcd27.stasyandex.dictionary.DictionaryPresenter;
import com.github.rcd27.stasyandex.translation.TranslationPresenter;

import dagger.Component;

@ApplicationScope
@Component(modules = {ModelModule.class, PresenterModule.class})
public interface AppComponent {

    // FIXME: не должно быть никаких инжектов в презентер и модель.
    void inject(ModelImpl dataFromApi);

    void inject(BasePresenter basePresenter);

    void inject(TranslationPresenter translationPresenter);

    void inject(DictionaryPresenter dictionaryPresenter);

}
