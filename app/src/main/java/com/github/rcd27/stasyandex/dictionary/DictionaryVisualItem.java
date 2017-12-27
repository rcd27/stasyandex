package com.github.rcd27.stasyandex.dictionary;


import java.text.MessageFormat;
import java.util.List;

public class DictionaryVisualItem {
    private List<String> comaRaw;
    private List<String> meaningRaw;

    public DictionaryVisualItem(List<String> comaRaw, List<String> meaningRaw) {
        this.comaRaw = comaRaw;
        this.meaningRaw = meaningRaw;
    }

    public List<String> getComaRaw() {
        return comaRaw;
    }

    public List<String> getMeaningRaw() {
        return meaningRaw;
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0}\n({1})", comaRaw.toString(), meaningRaw.toString());
    }
}
