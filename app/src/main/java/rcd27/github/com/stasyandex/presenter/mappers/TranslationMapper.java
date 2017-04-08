package rcd27.github.com.stasyandex.presenter.mappers;


import rcd27.github.com.stasyandex.model.dto.translate.TranslationDTO;
import rcd27.github.com.stasyandex.presenter.visualobjects.Translation;
import rx.Observable;
import rx.functions.Func1;

public class TranslationMapper implements Func1<TranslationDTO, Translation> {
    //TODO FIXME очень кривой маппинг через лист. Мне не нравится. Но должен сработать.
    @Override
    public Translation call(TranslationDTO translationDTO) {
        TranslationDTO[] wrapper = new TranslationDTO[]{translationDTO};
        return Observable.from(wrapper)
                .map(transDTO -> new Translation(transDTO.getTranslationResult()))
                .toList()
                .toBlocking()
                .first()
                .get(0);
    }
}
