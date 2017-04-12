package rcd27.github.com.stasyandex.fragments.dictionary.model.dto;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/*Элемент для отображения в RecyclerView*/
public class Tr {
    @SerializedName("text")
    @Expose
    public String text;

    /*Часть речи, может отсутствовать*/
    //FIXME подумать на тему null при отсутствующем поле в JSON
    @SerializedName("pos")
    @Expose
    public String pos;

    /*Массив синонимов*/
    @SerializedName("syn")
    @Expose
    public List<Syn> syn = null;

    @SerializedName("mean")
    @Expose
    public List<Mean> mean = null;

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

    public List<Syn> getSyn() {
        return syn;
    }

    public void setSyn(List<Syn> syn) {
        this.syn = syn;
    }

    public List<Mean> getMean() {
        return mean;
    }

    public void setMean(List<Mean> mean) {
        this.mean = mean;
    }
}
