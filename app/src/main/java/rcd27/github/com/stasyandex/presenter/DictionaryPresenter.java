package rcd27.github.com.stasyandex.presenter;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rcd27.github.com.stasyandex.presenter.mappers.DictionaryDefinitionMapper;
import rcd27.github.com.stasyandex.presenter.mappers.DictionaryItemMapper;
import rcd27.github.com.stasyandex.presenter.visualobjects.DictionaryDefinition;
import rcd27.github.com.stasyandex.presenter.visualobjects.DictionaryItem;
import rcd27.github.com.stasyandex.view.fragments.DictionaryView;

public class DictionaryPresenter extends BasePresenter {

    private DictionaryView view;

    private DictionaryDefinitionMapper dictionaryDefinitionMapper =
            new DictionaryDefinitionMapper();
    private List<DictionaryDefinition> dictionaryDefinitionList;
    private DictionaryItemMapper dictionaryItemMapper = new DictionaryItemMapper();
    private List<DictionaryItem> dictionaryItemList;

    public DictionaryPresenter(DictionaryView view) {
        this.view = view;
    }

    public void onGetDictionaryResponse() {
        String textToGetDicFor = view.getDictionaryFor();
        //TODO недоделан маппинг в vo. Надо идти от самого Observable<DicResultDTO>
        DictionaryDefinition df = new DictionaryDefinition("привет", "сущ");
        List<DictionaryDefinition> dflist = new ArrayList<>();
        dflist.add(df);
        view.showDictionaryDefiniton(dflist);
        DictionaryItem dione = new DictionaryItem("hello",
                Arrays.asList("salutations", "hallo"), "сущ", Arrays.asList("(приветствие)"));
        view.showDictionaryDictionaryItems(Arrays.asList(dione));
    }
}
