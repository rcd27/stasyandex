package rcd27.github.com.stasyandex.presenter.dictionary;


import java.text.MessageFormat;

public class DictionaryVisualDefinition {
    String text;
    String pos;

    public DictionaryVisualDefinition(String text, String pos) {
        this.text = text;
        this.pos = pos;
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0}\n({1})", text, pos);
    }
}
