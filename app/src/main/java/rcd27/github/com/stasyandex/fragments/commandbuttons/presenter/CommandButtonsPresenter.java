package rcd27.github.com.stasyandex.fragments.commandbuttons.presenter;


import rcd27.github.com.stasyandex.presenter.BasePresenter;
import rcd27.github.com.stasyandex.fragments.commandbuttons.view.CommandButtonsView;

public class CommandButtonsPresenter extends BasePresenter {

    private CommandButtonsView view;

    public CommandButtonsPresenter(CommandButtonsView view) {
        this.view = view;
    }

    public void onStartTranslateClick() {
        view.startTranslateFragment();
    }

    public void onStartFavouritesCLick() {
        view.startFavouritesFragment();
    }
}
