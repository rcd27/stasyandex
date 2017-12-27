package com.github.rcd27.stasyandex.dictionary;


import java.util.List;

import com.github.rcd27.stasyandex.common.BaseView;

public interface DictionaryView extends BaseView{

    void showDefinition(DictionaryVisualDefinition definition);

    void showDictionaryItems(List<DictionaryVisualItem> items);

}
