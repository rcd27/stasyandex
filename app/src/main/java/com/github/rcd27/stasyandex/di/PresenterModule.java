package com.github.rcd27.stasyandex.di;

import dagger.Module;
import dagger.Provides;
import com.github.rcd27.stasyandex.model.Model;
import com.github.rcd27.stasyandex.model.ModelImpl;
import rx.subscriptions.CompositeSubscription;

@Module
public class PresenterModule {

    @Provides
    @ApplicationScope
    Model provideDataFromApi() {
        return new ModelImpl();
    }

    @Provides
    @ApplicationScope
    CompositeSubscription provideCompositeSubscription() {
        return new CompositeSubscription();
    }
}
