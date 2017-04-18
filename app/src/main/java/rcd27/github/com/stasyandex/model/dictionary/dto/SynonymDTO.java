package rcd27.github.com.stasyandex.model.dictionary.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/*Синоним*/
public class SynonymDTO {
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