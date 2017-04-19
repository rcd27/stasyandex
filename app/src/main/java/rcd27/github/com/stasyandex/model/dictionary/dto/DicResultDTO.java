package rcd27.github.com.stasyandex.model.dictionary.dto;


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
    //TODO то есть из них надо ещё достать DicTranslationDTO
    @SerializedName("definitionDTO")
    @Expose
    public List<DefinitionDTO> definitionDTO = null;

    public List<DefinitionDTO> getDefinitionDTO() {
        return definitionDTO;
    }

    public void setDefinitionDTO(List<DefinitionDTO> definitionDTO) {
        this.definitionDTO = definitionDTO;
    }
}

