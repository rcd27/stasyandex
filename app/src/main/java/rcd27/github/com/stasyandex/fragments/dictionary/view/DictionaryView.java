package rcd27.github.com.stasyandex.fragments.dictionary.view;


import java.util.List;

import rcd27.github.com.stasyandex.fragments.dictionary.model.dto.Def;
import rcd27.github.com.stasyandex.fragments.dictionary.model.dto.Tr;
import rcd27.github.com.stasyandex.fragments.dictionary.presenter.vo.DictionaryDefinition;

public interface DictionaryView {

    void showDef(Def def);

    void showEmpty();
}
