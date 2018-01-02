package com.github.rcd27.stasyandex.dictionary.visual;


import java.text.MessageFormat;
import java.util.List;

public class DictionaryVisualItem {

    private final List<String> comaRaw;
    private final List<String> meaningRaw;

    public DictionaryVisualItem(List<String> comaRaw, List<String> meaningRaw) {
        this.comaRaw = comaRaw;
        this.meaningRaw = meaningRaw;
    }

    List<String> getComaRaw() {
        return comaRaw;
    }

    List<String> getMeaningRaw() {
        return meaningRaw;
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0}\n({1})", comaRaw, meaningRaw);
    }
}
