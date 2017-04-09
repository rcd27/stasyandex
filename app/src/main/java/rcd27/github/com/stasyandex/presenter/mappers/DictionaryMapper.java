package rcd27.github.com.stasyandex.presenter.mappers;


import java.util.List;

import rcd27.github.com.stasyandex.model.dto.dictionary.Tr;
import rcd27.github.com.stasyandex.presenter.visualobjects.DictionaryItem;
import rx.functions.Func1;

public class DictionaryMapper implements Func1<List<Tr>, List<DictionaryItem>> {

    //TODO сделать маппер для элемента словаря.
    @Override
    public List<DictionaryItem> call(List<Tr> trs) {
        return null;
    }
}
