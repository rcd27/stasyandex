package rcd27.github.com.stasyandex.model.translation.dto;

import com.google.gson.annotations.SerializedName;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import rcd27.github.com.stasyandex.TextUtil;

/*
Собственно перевод.
translationResult оформлен листом, но в нём замечен пока только один элемент.
 */
public class Translation {
    @SerializedName("code")
    private int code;

    @SerializedName("lang")
    private String direction;

    @SerializedName("text")
    private List<String> translationResult = new ArrayList<>();

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public List<String> getTranslationResult() {
        return translationResult;
    }

    public void setTranslationResult(List<String> translationResult) {
        this.translationResult = translationResult;
    }

    @Override
    public String toString() {
        return MessageFormat.format("Code: {0}, Direction: {1}, Translation result: {2}",
                code, direction, translationResult.toString());
    }

    public String show() {
        return TextUtil.commaRawFromList(translationResult);
    }

    public boolean isEmpty() {
        return getTranslationResult().isEmpty();
    }
}
