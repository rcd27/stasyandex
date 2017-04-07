package rcd27.github.com.stasyandex.view.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import rcd27.github.com.stasyandex.R;
import rcd27.github.com.stasyandex.presenter.BasePresenter;

public class CommandButtonsFragment extends BaseFragment {
    @Bind(R.id.command_translate)

    Button translateButton;

    @Bind(R.id.command_favourite)
    Button favouritesButton;

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
