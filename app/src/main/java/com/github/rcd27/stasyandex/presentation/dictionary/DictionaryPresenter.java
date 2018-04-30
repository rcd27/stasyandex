package com.github.rcd27.stasyandex.presentation.dictionary;


import android.support.annotation.*;

import com.github.rcd27.stasyandex.model.business.dictionary.*;
import com.github.rcd27.stasyandex.presentation.*;

import io.reactivex.android.schedulers.*;
import io.reactivex.disposables.*;

public class DictionaryPresenter extends BasePresenter implements DictionaryContract.Presenter {

  private final DictionaryContract.View view;
  private final DictionaryInteractor interactor;

  public DictionaryPresenter(DictionaryContract.View view, DictionaryInteractor interactor) {
    this.view = view;
    this.interactor = interactor;
  }

  public void getDictionaryResponseFor(@NonNull String direction, @NonNull String text) {
    addDisposable(getDictionary(direction, text));
  }

  @NonNull
  private Disposable getDictionary(String direction, String text) {
    return interactor.getDicResultFor(direction, text, "ru")
        .observeOn(AndroidSchedulers.mainThread())
        .doOnError(throwable -> view.showEmpty())
        .subscribe(dicResult -> {
          if (!dicResult.definitionListIsEmptyOrNull()) {
            view.showDefinition(dicResult.getDefinition());
            view.showDictionaryItems(dicResult.getElementsList());
          } else {
            view.showEmpty();
          }
        });
  }
}