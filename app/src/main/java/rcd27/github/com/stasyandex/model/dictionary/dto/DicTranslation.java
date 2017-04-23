package rcd27.github.com.stasyandex.model.dictionary.dto;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DicTranslation {
    @SerializedName("text")
    @Expose
    public String text;

    @SerializedName("pos")
    @Expose
    public String pos;

    /*Массив синонимов*/
    @SerializedName("syn")
    @Expose
    public List<String> synonyms;

    @SerializedName("mean")
    @Expose
    public List<String> meanings;

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

    public List<String> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(List<String> synonyms) {
        this.synonyms = synonyms;
    }

    public List<String> getMeanings() {
        return meanings;
    }

    public void setMeanings(List<String> meanings) {
        this.meanings = meanings;
    }
}
