package rcd27.github.com.stasyandex.view;


import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;

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

    protected void makeToast(View view,String text) {
        Snackbar.make(view, text, Snackbar.LENGTH_LONG).show();
    }
}
