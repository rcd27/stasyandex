package rcd27.github.com.stasyandex.model.dto.dictionary;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/*Перевод*/
public class Tr {
    @SerializedName("text")
    @Expose
    public String text;

    /*Часть речи, может отсутствовать*/
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
}
