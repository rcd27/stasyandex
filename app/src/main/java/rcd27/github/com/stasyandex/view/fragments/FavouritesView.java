package rcd27.github.com.stasyandex.view.fragments;


import java.util.List;

import rcd27.github.com.stasyandex.model.data.Translation;

public interface FavouritesView extends View {

    void showHistory(List<Translation> vo);

    void sortFavourites(List<Translation> vo);

    void showEmptyHistory();

    String getTranslationToSearchInHistory();
}
