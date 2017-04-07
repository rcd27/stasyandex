package rcd27.github.com.stasyandex.view.fragments;


import android.support.v4.app.Fragment;

import rcd27.github.com.stasyandex.presenter.BasePresenter;

public abstract class BaseFragment extends Fragment {
    /*
    Базовый фрагмент. Нужно для того, чтобы презентеры
    отписывались от Observable при остановке приложения.
     */

    protected abstract BasePresenter getPresenter();

    @Override
    public void onStop() {
        super.onStop();
        if (null != getPresenter()) {
            getPresenter().onStop();
        }
    }
}
