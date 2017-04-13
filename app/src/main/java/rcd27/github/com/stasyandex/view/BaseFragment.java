package rcd27.github.com.stasyandex.view;


import android.support.v4.app.Fragment;

import rcd27.github.com.stasyandex.presenter.BasePresenter;

/*
Базовый фрагмент. Нужно для того, чтобы презентеры
отписывались от Observable при остановке приложения.
 */

public abstract class BaseFragment extends Fragment {
    protected abstract BasePresenter getPresenter();

    @Override
    public void onStop() {
        super.onStop();
        if (null != getPresenter()) {
            getPresenter().onStop();
        }
    }
}
