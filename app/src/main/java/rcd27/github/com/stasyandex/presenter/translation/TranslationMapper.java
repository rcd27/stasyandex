package rcd27.github.com.stasyandex.presenter.translation;


import rcd27.github.com.stasyandex.model.translation.dto.TranslationDTO;
import rcd27.github.com.stasyandex.presenter.visualobject.Translation;
import rx.Observable;
import rx.functions.Func1;

public class TranslationMapper implements Func1<TranslationDTO, Translation> {
    @Override
    public Translation call(TranslationDTO translationDTO) {
        return Observable.just(translationDTO)
                .map(transDTO -> new Translation(transDTO.getTranslationResult()))
                .toBlocking()
                .single();
    }
}
