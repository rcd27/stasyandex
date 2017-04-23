package rcd27.github.com.stasyandex.model.dictionary.dto;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/*Возвращается JSON'ом*/
public class DicResult {
    /*Заголовок результата - не используется*/
    @SerializedName("head")
    @Expose(deserialize = false)
    public Object nullhead;

    /*Массив словарных статей*/
    @SerializedName("def")
    @Expose
    private List<Definition> definition;

    public List<Definition> getDefinition() {
        return definition;
    }

    public void setDefinition(List<Definition> definition) {
        this.definition = definition;
    }
}

