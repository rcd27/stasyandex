package rcd27.github.com.stasyandex.fragments.dictionary.presenter;


import java.util.List;

import rcd27.github.com.stasyandex.fragments.dictionary.model.dto.Def;
import rcd27.github.com.stasyandex.fragments.dictionary.presenter.vo.DictionaryDefinition;
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
