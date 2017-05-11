package rcd27.github.com.stasyandex.view.dictionary;


import java.util.List;

import rcd27.github.com.stasyandex.model.dictionary.dto.Definition;
import rcd27.github.com.stasyandex.presenter.dictionary.DictionaryVisualDefinition;
import rcd27.github.com.stasyandex.presenter.dictionary.DictionaryVisualItem;
import rcd27.github.com.stasyandex.view.BaseView;

public interface DictionaryView extends BaseView{

    void showDefinition(DictionaryVisualDefinition definition);

    void showDictionaryItems(List<DictionaryVisualItem> items);

}
