package com.github.rcd27.stasyandex.model.business.dictionary;

import com.github.rcd27.airbag.*;
import com.github.rcd27.stasyandex.model.data.dictionary.*;
import com.github.rcd27.stasyandex.presentation.dictionary.*;


public class DictionaryInteractor {

  private final DictionaryContract.Api api;

  public DictionaryInteractor(DictionaryContract.Api api) {
    this.api = api;
  }

  public ApiRequest<DictionaryResponse> getDicResultFor(String direction, String text, String language) {
    return new ApiRequest<>(api.getDicResultFor(direction, text, language));
  }
}
