package rcd27.github.com.stasyandex.presenter.mappers;


import java.util.List;

import rcd27.github.com.stasyandex.model.dto.dictionary.Def;
import rcd27.github.com.stasyandex.presenter.visualobjects.DictionaryDefinition;
import rx.Observable;
import rx.functions.Func1;

public class DictionaryDefinitionMapper implements Func1<List<Def>, List<DictionaryDefinition>> {

    @Override
    public List<DictionaryDefinition> call(List<Def> defs) {
        return Observable.from(defs)
                .map(def -> new DictionaryDefinition(def.getText(), def.getPos()))
                .toList()
                .toBlocking()
                .first();
    }
}
