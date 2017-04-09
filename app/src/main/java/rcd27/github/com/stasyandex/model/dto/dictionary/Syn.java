package rcd27.github.com.stasyandex.model.dto.dictionary;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//FIXME это похоже на мельчайший атом сущности словаря
/*Синоним*/
public class Syn {
    @SerializedName("text")
    @Expose
    public String text;

    @SerializedName("pos")
    @Expose(deserialize = false)
    public String pos;
}
