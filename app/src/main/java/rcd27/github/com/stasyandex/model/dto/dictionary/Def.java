package rcd27.github.com.stasyandex.model.dto.dictionary;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/*Словарная статья*/
public class Def {
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
    public List<Tr> tr = null;
}
