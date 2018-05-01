package com.github.rcd27.stasyandex.presentation;


import io.reactivex.disposables.*;

public abstract class BasePresenter {

  private final CompositeDisposable compositeDisposable;

  protected BasePresenter() {
    this.compositeDisposable = new CompositeDisposable();
  }

  protected void addDisposable(Disposable d) {
    compositeDisposable.add(d);
  }

  void onStop() {
    compositeDisposable.clear();
  }
}
