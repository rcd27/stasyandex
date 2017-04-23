package rcd27.github.com.stasyandex.model.dictionary.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/*Словарная статья*/
public class Definition {
    @SerializedName("text")
    @Expose
    public String text;

    /*Часть речи, может отсутствовать*/
    @SerializedName("pos")
    @Expose
    public String pos;

    @SerializedName("anm")
    @Expose
    public String anm;

    /*Массив переводов*/
    @SerializedName("tr")
    @Expose
    public List<DicTranslation> dicTranslation;

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

    public String getAnm() {
        return anm;
    }

    public void setAnm(String anm) {
        this.anm = anm;
    }

    public List<DicTranslation> getDicTranslation() {
        return dicTranslation;
    }

    public void setDicTranslation(List<DicTranslation> dicTranslation) {
        this.dicTranslation = dicTranslation;
    }
}