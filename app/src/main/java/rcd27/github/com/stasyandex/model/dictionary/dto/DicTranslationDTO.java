package rcd27.github.com.stasyandex.model.dictionary.dto;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DicTranslationDTO {
    @SerializedName("text")
    @Expose
    public String text;

    /*Часть речи, может отсутствовать*/
    //FIXME подумать на тему null при отсутствующем поле в JSON
    @SerializedName("pos")
    @Expose
    public String pos;

    /*Массив синонимов*/
    @SerializedName("synonymDTO")
    @Expose
    public List<SynonymDTO> synonymDTO = null;

    @SerializedName("meaninigDTO")
    @Expose
    public List<MeaninigDTO> meaninigDTO = null;

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

    public List<SynonymDTO> getSynonymDTO() {
        return synonymDTO;
    }

    public void setSynonymDTO(List<SynonymDTO> synonymDTO) {
        this.synonymDTO = synonymDTO;
    }

    public List<MeaninigDTO> getMeaninigDTO() {
        return meaninigDTO;
    }

    public void setMeaninigDTO(List<MeaninigDTO> meaninigDTO) {
        this.meaninigDTO = meaninigDTO;
    }
}
