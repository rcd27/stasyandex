package rcd27.github.com.stasyandex.fragments.dictionary.view;


import java.util.List;

import rcd27.github.com.stasyandex.fragments.dictionary.model.dto.Def;
import rcd27.github.com.stasyandex.fragments.dictionary.model.dto.Tr;
import rcd27.github.com.stasyandex.fragments.dictionary.presenter.vo.DictionaryDefinition;

public interface DictionaryView {

    /*Аналогично интерфейсу переводчика. Берём текст из...*/
    String getDictionaryFor();

    /*Отображаем определение слова: оригинал
    *                               часть речи*/
    void showDictionaryDefiniton(DictionaryDefinition definition);

    /*Отображаем список элементов, полученных из API*/
    void showDictionaryDictionaryItems(List<Tr> items);

    void showEmpty();

    void showDef(Def def);
}
