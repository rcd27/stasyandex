package rcd27.github.com.stasyandex.model.translation.dto;

import com.google.gson.annotations.SerializedName;

import java.text.MessageFormat;

/*
Получаемый DTO, Ответ API на команду detect.
 */
public class ProbableLanguage {
    @SerializedName("code")
    private int code;
    @SerializedName("lang")
    private String language;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return MessageFormat.format("Code: {0}, Probable language: {1}", code, language);
    }
}
