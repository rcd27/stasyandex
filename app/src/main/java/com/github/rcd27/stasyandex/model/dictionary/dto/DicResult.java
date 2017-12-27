package com.github.rcd27.stasyandex.model.dictionary.dto;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import com.github.rcd27.stasyandex.presenter.dictionary.DictionaryVisualDefinition;
import com.github.rcd27.stasyandex.presenter.dictionary.DictionaryVisualItem;

/*Возвращается JSON'ом*/
public class DicResult {
    /*Заголовок результата - не используется*/
    @SerializedName("head")
    @Expose(deserialize = false)
    public Object nullhead;

    /*Массив словарных статей*/
    @SerializedName("def")
    @Expose
    private List<Definition> definitionList;

    private List<Definition> getDefinitionList() {
        return definitionList;
    }

    public void setDefinitionList(List<Definition> definitionList) {
        this.definitionList = definitionList;
    }

    public boolean definitionListIsEmptyOrNull(){
        return definitionList.isEmpty() || definitionList == null;
    }

    public DictionaryVisualDefinition getDefinition() {
            Definition theFirstDef = definitionList.get(0);
            return new DictionaryVisualDefinition(theFirstDef.getText(), theFirstDef.getPos());
    }

    public List<DictionaryVisualItem> getElementsList() {
            Definition theFirstDef = definitionList.get(0);
            List<DictionaryVisualItem> dicVoList = new ArrayList<>();
            for (DicTranslation dicTranslation : theFirstDef.getDicTranslation()) {
                //Первая линия слов, ч/з зпт
                List<String> commaRaw = new ArrayList<>();
                String firstTrWord = dicTranslation.getText();
                commaRaw.add(firstTrWord);
                //добавляем синонимы
                List<Synonym> allSynsForFirstTrWord = dicTranslation.getSynonyms();
                if (null != allSynsForFirstTrWord) {
                    for (Synonym syn : allSynsForFirstTrWord) {
                        commaRaw.add(syn.text);
                    }
                }
                //линия значений
                List<String> meaningRaw = new ArrayList<>();
                List<Meaning> meanings = dicTranslation.getMeanings();
                if (null != meanings) {
                    for (Meaning m : meanings) {
                        meaningRaw.add(m.getText());
                    }
                }

                dicVoList.add(new DictionaryVisualItem(commaRaw, meaningRaw));
            }
            return dicVoList;

    }
}



