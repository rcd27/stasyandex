package rcd27.github.com.stasyandex.view.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import butterknife.Bind;
import butterknife.ButterKnife;
import rcd27.github.com.stasyandex.R;
import rcd27.github.com.stasyandex.presenter.BasePresenter;

public class CommandButtonsFragment extends BaseFragment {
    @Bind(R.id.ib_command_translate)
    ImageButton translateButton;

    @Bind(R.id.ib_command_favourite)
    ImageButton favouritesButton;

    @Bind(R.id.ib_command_preferencies)
    ImageButton preferencesButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.command_buttons_layout, container, false);
        ButterKnife.bind(this, view);

        //TODO тут ещё логика из android-mvp
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ButterKnife.bind(getActivity());
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }
}
