package rcd27.github.com.stasyandex.view.dictionary;


import java.util.List;

import rcd27.github.com.stasyandex.model.dictionary.dto.Definition;

public interface DictionaryView {

    void showDef(Definition definition);

    void showEmpty();
}
