package rcd27.github.com.stasyandex.fragments.favourites.view;


import java.util.List;

import rcd27.github.com.stasyandex.view.BaseView;
import rcd27.github.com.stasyandex.fragments.translation.model.dto.TranslationDTO;

public interface FavouritesView extends BaseView {

    void showHistory(List<TranslationDTO> vo);

    void sortFavourites(List<TranslationDTO> vo);

    void showEmptyHistory();

    String getTranslationToSearchInHistory();
}
