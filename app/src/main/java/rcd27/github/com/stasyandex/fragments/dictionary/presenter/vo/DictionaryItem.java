package rcd27.github.com.stasyandex.fragments.dictionary.presenter.vo;


import java.util.List;

/*
Визуальный объект для представления элемента в списке словаря.
 */
public class DictionaryItem {

    /*
    [hi](text), [hello, hallo, salutation](syn)
    [сущ](pos)
    [(приетствие)](mean)

     */
    private String text;
    private List<String> syn;
    private String pos;
    private List<String> mean;

    public DictionaryItem(String text, List<String> syn, String pos, List<String> mean) {
        this.text = text;
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
