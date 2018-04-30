package com.github.rcd27.stasyandex.presentation.dictionary;


import android.support.annotation.*;

import com.github.rcd27.airbag.*;
import com.github.rcd27.stasyandex.model.business.dictionary.*;
import com.github.rcd27.stasyandex.model.data.dictionary.*;
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

    ApiRequest<DictionaryResponse> dictionaryRequest = interactor.getDicResultFor(direction, text, "ru");

    Disposable errors = dictionaryRequest.errors
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(e -> {
          view.showError(e.getMessage());
          view.showEmpty();
        });

    Disposable dictionary = dictionaryRequest.response
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(dictionaryResponse -> {
          view.showDefinition(dictionaryResponse.getDefinition());
          view.showDictionaryItems(dictionaryResponse.getElementsList());
        }, throwable -> {
          // FIXME: this can still be broken in Observer's block
        });

    Disposable state = dictionaryRequest.state
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(view::showState);

    addDisposable(dictionaryRequest.execute(errors, dictionary, state));
  }
}