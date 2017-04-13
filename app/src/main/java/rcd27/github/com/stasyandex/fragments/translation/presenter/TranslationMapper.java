package rcd27.github.com.stasyandex.fragments.translation.presenter;


import rcd27.github.com.stasyandex.fragments.translation.model.dto.TranslationDTO;
import rcd27.github.com.stasyandex.fragments.translation.presenter.vo.Translation;
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
