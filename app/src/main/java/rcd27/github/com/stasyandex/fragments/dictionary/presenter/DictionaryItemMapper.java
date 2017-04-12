package rcd27.github.com.stasyandex.fragments.dictionary.presenter;


import java.util.List;

import rcd27.github.com.stasyandex.fragments.dictionary.model.dto.Mean;
import rcd27.github.com.stasyandex.fragments.dictionary.model.dto.Syn;
import rcd27.github.com.stasyandex.fragments.dictionary.model.dto.Tr;
import rcd27.github.com.stasyandex.fragments.dictionary.presenter.vo.DictionaryItem;
import rx.Observable;
import rx.functions.Func1;

public class DictionaryItemMapper implements Func1<List<Tr>, List<DictionaryItem>> {

    @Override
    public List<DictionaryItem> call(List<Tr> trs) {
        SynToString synToString = new SynToString();
        MeanToString meanToString = new MeanToString();

        return Observable.from(trs)
                .map(tr -> new DictionaryItem(tr.getText(),
                        synToString.call(tr.getSyn()),
                        tr.getPos(),
                        meanToString.call(tr.getMean())))
                .toList()
                .toBlocking()
                .first();
    }

    //TODO запилить внятный дженерик, чтобы не позориться опять с дублированным кодом.
    private class SynToString implements Func1<List<Syn>, List<String>> {
        @Override
        public List<String> call(List<Syn> syns) {
            return Observable.from(syns)
                    .map(Syn::getText)
                    .toList()
                    .toBlocking()
                    .first();
        }
    }

    private class MeanToString implements Func1<List<Mean>, List<String>> {
        @Override
        public List<String> call(List<Mean> meen) {
            return Observable.from(meen)
                    .map(Mean::getText)
                    .toList()
                    .toBlocking()
                    .first();
        }

    }
}