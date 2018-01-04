package com.github.rcd27.stasyandex;

import android.app.Application;

import com.github.rcd27.stasyandex.di.AppComponent;
import com.github.rcd27.stasyandex.di.DaggerAppComponent;

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

        appComponent = DaggerAppComponent.builder()
//                .apiModule(new ApiModule()) // FIXME: работает и без явного указания этой строки.
                .build();
    }
}
