package rcd27.github.com.stasyandex.model.dictionary.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/*Значение*/
public class MeaninigDTO {
    @SerializedName("text")
    @Expose
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
