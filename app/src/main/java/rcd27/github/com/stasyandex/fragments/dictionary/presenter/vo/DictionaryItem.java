package rcd27.github.com.stasyandex.fragments.dictionary.presenter.vo;


import java.text.MessageFormat;
import java.util.List;

import static rcd27.github.com.stasyandex.StasyandexTextUtils.commaRawFromList;

/*
Визуальный объект для представления элемента в списке словаря.
 */
public class DictionaryItem {

    /*
    [hi](text), [hello, hallo, salutation](syn)
    [(приетствие)](mean)
     */

    private String text;
    private List<String> syn;
    private List<String> mean;

    public DictionaryItem(String text, List<String> syn, List<String> mean) {
        this.text = text;
        this.syn = syn;
        this.mean = mean;
    }

    public String firstLine() {
        return MessageFormat.format("{0}, {1}", text, commaRawFromList(syn));
    }

    public String meaning() {
        return MessageFormat.format("({0})", commaRawFromList(mean));
    }
}
