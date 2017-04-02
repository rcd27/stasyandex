package rcd27.github.com.stasyandex.model.data;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AvailableLanguages {
    @SerializedName("dirs")
    private List<String> directions;

    @SerializedName("langs")
    private
    Langs languages;

    public List<String> getDirections() {
        return directions;
    }

    public void setDirections(List<String> directions) {
        this.directions = directions;
    }

    public Langs getLanguages() {
        return languages;
    }

    public void setLanguages(Langs languages) {
        this.languages = languages;
    }
}
