package rcd27.github.com.stasyandex.fragments.dictionary.presenter.vo;


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

    public void setText(String text) {
        this.text = text;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }
}
