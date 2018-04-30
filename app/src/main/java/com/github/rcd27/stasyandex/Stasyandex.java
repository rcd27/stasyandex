package com.github.rcd27.stasyandex;

import android.app.Application;

import com.github.rcd27.stasyandex.di.AppComponent;
import com.github.rcd27.stasyandex.di.DaggerAppComponent;

import timber.log.Timber;

// TODO: убрать subscribeOn(..) для всех вызовов API - теперь это по дефолту
public class Stasyandex extends Application {

    public static Stasyandex instance;

    public static Stasyandex getInstance() {
        return instance;
    }

    protected AppComponent appComponent;

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        appComponent = DaggerAppComponent.builder().build();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}