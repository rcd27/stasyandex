package rcd27.github.com.stasyandex.model.dto.dictionary;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/*Возвращается JSON'ом*/
public class DicResultDTO {
    /*Заголовок результата - не используется*/
    @SerializedName("head")
    @Expose(deserialize = false)
    public Object nullhead;

    /*Массив словарных статей*/
    //TODO то есть из них надо ещё достать Tr
    @SerializedName("def")
    @Expose
    public List<Def> def = null;

    public List<Def> getDef() {
        return def;
    }

    public void setDef(List<Def> def) {
        this.def = def;
    }
}

