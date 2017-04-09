package rcd27.github.com.stasyandex.presenter.visualobjects;


import java.util.List;

/*
Визуальный объект для представления элемента в списке словаря.
 */
public class DictionaryItem {

    private String text;
    private List<String> syn;
    private String pos;
    private String mean;

    public DictionaryItem(String textSyn, List<String> syn, String pos, String mean) {
        this.text = textSyn;
        this.syn = syn;
        this.pos = pos;
        this.mean = mean;
    }

    public String getTextSyn() {
        return "hi, hello, hallo, salutation";
    }

    public String getMean() {
        return "(приветствие)";
    }
}
