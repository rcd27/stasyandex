package rcd27.github.com.stasyandex.model.dictionary;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/*Словарная статья*/
public class Def {
    @SerializedName("text")
    @Expose
    public String text;

    /*Часть речи, может отсутствовать*/
    //TODO освежить в памяти, что там про null в JSON'e
    @SerializedName("pos")
    @Expose
    public String pos;

    @SerializedName("anm")
    @Expose
    public String anm;

    /*Массив переводов*/
    @SerializedName("tr")
    @Expose
    public List<Tr> tr = null;

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

    public List<Tr> getTr() {
        return tr;
    }

    public void setTr(List<Tr> tr) {
        this.tr = tr;
    }
}
