package com.github.rcd27.stasyandex.data.dictionary;


import com.github.rcd27.stasyandex.presentation.dictionary.visual.*;
import com.google.gson.annotations.*;

import java.util.*;

public class DicResult {
  /*Массив словарных статей*/
  @SerializedName("def") public final List<Definition> definitionList;

  public DicResult(List<Definition> definitionList) {
    this.definitionList = definitionList;
  }

  public boolean definitionListIsEmptyOrNull() {
    return definitionList.isEmpty();
  }

  public DictionaryVisualDefinition getDefinition() {
    Definition theFirstDef = definitionList.get(0);
    return new DictionaryVisualDefinition(theFirstDef.text, theFirstDef.pos);
  }

  public List<DictionaryVisualItem> getElementsList() {
    Definition theFirstDef = definitionList.get(0);
    List<DictionaryVisualItem> dicVoList = new ArrayList<>();
    for (DicTranslation dicTranslation : theFirstDef.dicTranslation) {
      //Первая линия слов, ч/з зпт
      List<String> commaRaw = new ArrayList<>();
      String firstTrWord = dicTranslation.text;
      commaRaw.add(firstTrWord);
      //добавляем синонимы
      List<Synonym> allSynsForFirstTrWord = dicTranslation.synonyms;
      if (null != allSynsForFirstTrWord) {
        for (Synonym syn : allSynsForFirstTrWord) {
          commaRaw.add(syn.text);
        }
      }
      //линия значений
      List<String> meaningRaw = new ArrayList<>();
      List<Meaning> meanings = dicTranslation.meanings;
      if (null != meanings) {
        for (Meaning m : meanings) {
          meaningRaw.add(m.text);
        }
      }

      dicVoList.add(new DictionaryVisualItem(commaRaw, meaningRaw));
    }

    return dicVoList;
  }
}



