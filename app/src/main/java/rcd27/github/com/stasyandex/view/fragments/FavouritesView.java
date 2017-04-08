package rcd27.github.com.stasyandex.view.fragments;


import java.util.List;

import rcd27.github.com.stasyandex.model.dto.TranslationDTO;

public interface FavouritesView extends View {

    void showHistory(List<TranslationDTO> vo);

    void sortFavourites(List<TranslationDTO> vo);

    void showEmptyHistory();

    String getTranslationToSearchInHistory();
}
