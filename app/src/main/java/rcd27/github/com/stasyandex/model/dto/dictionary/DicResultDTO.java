package rcd27.github.com.stasyandex.model.dto.dictionary;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/*Возвращается JSON'ом*/
public class DicResultDTO {
    /*Заголовок результата - не используется*/
    @SerializedName("head")
    @Expose
    public Head head;

    /*Массив словарных статей*/
    @SerializedName("def")
    @Expose
    public List<Def> def = null;
}

