package com.github.rcd27.stasyandex.model.business.dictionary;

import com.github.rcd27.stasyandex.model.data.dictionary.*;
import com.github.rcd27.stasyandex.presentation.dictionary.*;

import io.reactivex.*;


public class DictionaryInteractor {

  private final DictionaryContract.Api api;

  public DictionaryInteractor(DictionaryContract.Api api) {
    this.api = api;
  }

  public Single<DicResult> getDicResultFor(String direction, String text, String language) {
    return api.getDicResultFor(direction, text, language);
  }
}
