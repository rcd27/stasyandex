package com.github.rcd27.stasyandex.view.dictionary;


import java.util.List;

import com.github.rcd27.stasyandex.presenter.dictionary.DictionaryVisualDefinition;
import com.github.rcd27.stasyandex.presenter.dictionary.DictionaryVisualItem;
import com.github.rcd27.stasyandex.view.BaseView;

public interface DictionaryView extends BaseView{

    void showDefinition(DictionaryVisualDefinition definition);

    void showDictionaryItems(List<DictionaryVisualItem> items);

}
