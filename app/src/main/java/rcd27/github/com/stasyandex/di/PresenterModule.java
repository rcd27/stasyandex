package rcd27.github.com.stasyandex.di;

import dagger.Module;
import dagger.Provides;
import rcd27.github.com.stasyandex.model.Model;
import rcd27.github.com.stasyandex.model.ModelImpl;
import rx.subscriptions.CompositeSubscription;

@Module
public class PresenterModule {

    @Provides
    @ApplicationScope
    Model provideDataFromApi() {
        return new ModelImpl();
    }

    @Provides
    CompositeSubscription provideCompositeSubscription() {
        return new CompositeSubscription();
    }
}
