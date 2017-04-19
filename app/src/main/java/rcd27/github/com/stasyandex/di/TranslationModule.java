package rcd27.github.com.stasyandex.di;


import dagger.Module;
import dagger.Provides;
import rcd27.github.com.stasyandex.presenter.translation.TranslationPresenter;
import rcd27.github.com.stasyandex.view.translation.TranslationFragment;
import rcd27.github.com.stasyandex.view.translation.TranslationView;

@Module
public class TranslationModule {

    TranslationView view;
    TranslationFragment.TranslateButtonListener listener;

    public TranslationModule(TranslationView view, TranslationFragment.TranslateButtonListener li) {
        this.view = view;
        this.listener = li;
    }

    @Provides
    @ApplicationScope
    TranslationPresenter translationPresenter() {
        return new TranslationPresenter(view);
    }
}
