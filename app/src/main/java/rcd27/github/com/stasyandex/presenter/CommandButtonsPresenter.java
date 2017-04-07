package rcd27.github.com.stasyandex.presenter;


import rcd27.github.com.stasyandex.view.fragments.CommandButtonsView;

public class CommandButtonsPresenter extends BasePresenter  {

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
