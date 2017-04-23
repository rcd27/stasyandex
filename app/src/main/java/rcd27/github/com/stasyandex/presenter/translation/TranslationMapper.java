package rcd27.github.com.stasyandex.presenter.translation;


import javax.inject.Inject;

import rcd27.github.com.stasyandex.model.translation.dto.Translation;
import rx.Observable;
import rx.functions.Func1;

public class TranslationMapper implements Func1<Translation, rcd27.github.com.stasyandex.presenter.visualobject.Translation> {

    @Inject
    public TranslationMapper() {
    }

    @Override
    public rcd27.github.com.stasyandex.presenter.visualobject.Translation call(Translation translation) {
        return Observable.just(translation)
                .map(transDTO -> new rcd27.github.com.stasyandex.presenter.visualobject.Translation(transDTO.getTranslationResult()))
                .toBlocking()
                .single();
    }
}
