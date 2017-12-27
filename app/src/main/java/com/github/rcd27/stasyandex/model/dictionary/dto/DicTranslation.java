package com.github.rcd27.stasyandex.model.dictionary.dto;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/*----------------------------------------- [tr]
1. [tr.text],[tr.getSyn[0]],[tr.getSyn[..]]
   ([tr.pos])
   ----------------------------------------
 */

public class DicTranslation {
    @SerializedName("text")
    @Expose
    public String text;

    @SerializedName("pos")
    @Expose
    private String pos;

    @SerializedName("syn")
    @Expose
    private List<Synonym> synonyms;

    @SerializedName("mean")
    @Expose()
    private List<Meaning> meanings;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public List<Synonym> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(List<Synonym> synonyms) {
        this.synonyms = synonyms;
    }

    public List<Meaning> getMeanings() {
        return meanings;
    }

    public void setMeanings(List<Meaning> meanings) {
        this.meanings = meanings;
    }
}
