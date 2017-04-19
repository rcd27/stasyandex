package rcd27.github.com.stasyandex.di;


import dagger.Module;
import dagger.Provides;
import rcd27.github.com.stasyandex.presenter.translation.TranslationPresenter;
import rcd27.github.com.stasyandex.view.translation.TranslationView;

@Module
public class TranslationModule {

    TranslationView view;

    public TranslationModule(TranslationView view) {
        this.view = view;
    }

    @Provides
    @ApplicationScope
    TranslationPresenter translationPresenter() {
        return new TranslationPresenter(view);
    }
}
