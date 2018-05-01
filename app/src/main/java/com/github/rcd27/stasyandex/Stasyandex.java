package com.github.rcd27.stasyandex;

import android.app.*;

import com.github.rcd27.stasyandex.di.*;

import timber.log.*;

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