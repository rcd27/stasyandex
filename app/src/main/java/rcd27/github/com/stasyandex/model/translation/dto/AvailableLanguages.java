package rcd27.github.com.stasyandex.model.translation.dto;


import com.google.gson.annotations.SerializedName;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

public class AvailableLanguages {

    @SerializedName("dirs")
    private List<String> directions;

    @SerializedName("langs")
    private
    Map<String, String> languages;

    public List<String> getDirections() {
        return directions;
    }

    public void setDirections(List<String> directions) {
        this.directions = directions;
    }

    public Map<String, String> getLanguages() {
        return languages;
    }

    public void setLanguages(Map<String, String> languages) {
        this.languages = languages;
    }

    public boolean isEmpty() {
        return directions.isEmpty() && languages.isEmpty();
    }

    @Override
    public String toString() {
        return MessageFormat.format("Directions: {0}, Languages: {1}", directions, languages);
    }
}
