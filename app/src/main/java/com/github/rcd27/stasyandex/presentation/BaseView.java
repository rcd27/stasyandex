package com.github.rcd27.stasyandex.presentation;

import com.github.rcd27.airbag.*;

public interface BaseView {

  void showError(String error);

  void showEmpty();

  void showState(ApiRequest.RequestState state);
}
