package rcd27.github.com.stasyandex.model;


import rcd27.github.com.stasyandex.model.dto.AvailableLanguagesDTO;
import rcd27.github.com.stasyandex.model.dto.ProbableLanguageDTO;
import rcd27.github.com.stasyandex.model.dto.TranslationDTO;
import rx.Observable;

public interface Model {

    Observable<AvailableLanguagesDTO> getAvailableLanguages(String forLanguage);

    Observable<ProbableLanguageDTO> getProbableLanguage(String forText);

    Observable<TranslationDTO> getTranslation(String forText, String direction);

}
