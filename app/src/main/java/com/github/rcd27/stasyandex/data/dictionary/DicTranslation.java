package com.github.rcd27.stasyandex.data.dictionary;


import com.google.gson.annotations.*;

import java.util.*;

/*----------------------------------------- [tr]
1. [tr.text],[tr.getSyn[0]],[tr.getSyn[..]]
   ([tr.pos])
   ----------------------------------------
 */

public class DicTranslation {
  @SerializedName("text") public final String text;
  @SerializedName("pos") public final String pos;
  @SerializedName("syn") public final List<Synonym> synonyms;
  @SerializedName("mean") public final List<Meaning> meanings;

  public DicTranslation(
      String text,
      String pos,
      List<Synonym> synonyms,
      List<Meaning> meanings) {

    this.text = text;
    this.pos = pos;
    this.synonyms = synonyms;
    this.meanings = meanings;
  }
}
