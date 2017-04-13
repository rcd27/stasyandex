package rcd27.github.com.stasyandex.fragments.dictionary.presenter.vo;


import java.text.MessageFormat;

public class DictionaryDefinition {

    private String text;
    private String pos;

    public DictionaryDefinition(String text, String pos) {
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
        return MessageFormat.format("Text: {0}, Pos: {1}", getText(), getPos());
    }
}
