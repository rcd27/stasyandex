package rcd27.github.com.stasyandex.view.dictionary;


import java.util.List;

import rcd27.github.com.stasyandex.model.dictionary.dto.Definition;
import rcd27.github.com.stasyandex.presenter.dictionary.DictionaryVisualDefinition;
import rcd27.github.com.stasyandex.presenter.dictionary.DictionaryVisualItem;

public interface DictionaryView {

    void showDefinition(DictionaryVisualDefinition definition);

    void showDictionaryItems(List<DictionaryVisualItem> items);

    void showEmpty();
}
