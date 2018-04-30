package com.github.rcd27.stasyandex.presentation;


import android.support.design.widget.*;
import android.support.v4.app.*;
import android.view.*;

public abstract class BaseFragment extends Fragment {
  protected abstract BasePresenter getPresenter();

  @Override
  public void onStop() {
    super.onStop();
    if (null != getPresenter()) {
      getPresenter().onStop();
    }
  }

  protected void makeToast(View view, String text) {
    Snackbar.make(view, text, Snackbar.LENGTH_LONG).show();
  }
}
