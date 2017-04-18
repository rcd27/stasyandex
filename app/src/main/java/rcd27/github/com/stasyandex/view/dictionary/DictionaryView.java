package rcd27.github.com.stasyandex.view.dictionary;


import rcd27.github.com.stasyandex.model.dictionary.dto.DefinitionDTO;

public interface DictionaryView {

    void showDef(DefinitionDTO definitionDTO);

    void showEmpty();
}
