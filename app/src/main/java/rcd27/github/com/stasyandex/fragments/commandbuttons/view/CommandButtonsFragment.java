package rcd27.github.com.stasyandex.fragments.commandbuttons.view;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import rcd27.github.com.stasyandex.ActivityCallback;
import rcd27.github.com.stasyandex.R;
import rcd27.github.com.stasyandex.fragments.commandbuttons.presenter.CommandButtonsPresenter;
import rcd27.github.com.stasyandex.presenter.BasePresenter;
import rcd27.github.com.stasyandex.view.BaseFragment;

public class CommandButtonsFragment extends BaseFragment implements CommandButtonsView {
    private final String TAG = getClass().getSimpleName();

    @Bind(R.id.command_translate)
    Button translateButton;

    @Bind(R.id.command_favourite)
    Button favouritesButton;

    private ActivityCallback activityCallback;

    private CommandButtonsPresenter presenter = new CommandButtonsPresenter(this);

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            activityCallback = (ActivityCallback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement activityCallback");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.command_buttons_layout, container, false);
        ButterKnife.bind(this, view);

        //TODO сделать так, чтобы переключалось, только если не на этом фрагменте
        translateButton.setOnClickListener(v -> startTranslateFragment());
        favouritesButton.setOnClickListener(v -> startFavouritesFragment());

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
        return presenter;
    }

    @Override
    public void showError(String error) {
        Log.e(TAG, "ERROR");
    }

    @Override
    public void startTranslateFragment() {
        activityCallback.startTranslationFragment();
    }

    @Override
    public void startFavouritesFragment() {
        activityCallback.startFavouritesFragment();
    }
}
