package rcd27.github.com.stasyandex.model.dto;


import com.google.gson.annotations.SerializedName;

import java.text.MessageFormat;
import java.util.List;

/*
DTO объект для всех доступных языках перевода.
Задача: получать его один раз при запуске приложения.
Мапить в объект для визуализации.
 */

public class AvailableLanguagesDTO {

    @SerializedName("dirs")
    private List<String> directions;

    //TODO изучить вопрос перевода этого дерьма в нормальный List<String>,чем он по сути и является.
    @SerializedName("langs")
    private
    LangsDTO languages;

    public List<String> getDirections() {
        return directions;
    }

    public void setDirections(List<String> directions) {
        this.directions = directions;
    }

    public LangsDTO getLanguages() {
        return languages;
    }

    public void setLanguages(LangsDTO languages) {
        this.languages = languages;
    }

    public boolean isEmpty() {
        //TODO поменять на languages.isEmpty()
        return directions.isEmpty() && languages == null;
    }

    @Override
    public String toString() {
        return MessageFormat.format("Directions: {0}, Languages: {1}", directions, languages);
    }
}
