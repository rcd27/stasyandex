package com.github.rcd27.stasyandex.dictionary.visual;


import java.text.MessageFormat;

public class DictionaryVisualDefinition {

    private final String text;
    private final String pos;

    public DictionaryVisualDefinition(String text, String pos) {
        this.text = text;
        this.pos = pos;
    }

    public String getText() {
        return text;
    }

    public String getPos() {
        return pos;
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0}\n({1})", text, pos);
    }
}
