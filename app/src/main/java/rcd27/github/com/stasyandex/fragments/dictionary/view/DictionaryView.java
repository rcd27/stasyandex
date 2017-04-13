package rcd27.github.com.stasyandex.fragments.dictionary.view;


import java.util.List;

import rcd27.github.com.stasyandex.fragments.dictionary.presenter.vo.DictionaryDefinition;
import rcd27.github.com.stasyandex.fragments.dictionary.presenter.vo.DictionaryItem;

public interface DictionaryView {

    //TODO как эта вьющка будет читать из TextView переводчика? Никак.
    /*Аналогично интерфейсу переводчика. Берём текст из...*/
    String getDictionaryFor();

    //TODO вообще-то API подразумевает возврат нескольких Def статей. Отображаем только одну?
    /*Отображаем определение слова: оригинал
    *                               часть речи*/
    void showDictionaryDefiniton(DictionaryDefinition definition);

    /*Отображаем список элементов, полученных из API*/
    void showDictionaryDictionaryItems(List<DictionaryItem> items);

    void showEmpty();
}
