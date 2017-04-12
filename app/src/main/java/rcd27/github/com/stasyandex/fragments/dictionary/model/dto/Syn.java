package rcd27.github.com.stasyandex.fragments.dictionary.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//FIXME это похоже на мельчайший атом сущности словаря
/*Синоним*/
public class Syn {
    @SerializedName("text")
    @Expose
    private String text;

    @SerializedName("pos")
    @Expose(deserialize = false)
    public String pos;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
