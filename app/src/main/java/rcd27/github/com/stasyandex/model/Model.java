package rcd27.github.com.stasyandex.model;


import rcd27.github.com.stasyandex.model.dto.dictionary.DicResultDTO;
import rcd27.github.com.stasyandex.model.dto.translate.AvailableLanguagesDTO;
import rcd27.github.com.stasyandex.model.dto.translate.ProbableLanguageDTO;
import rcd27.github.com.stasyandex.model.dto.translate.TranslationDTO;
import rx.Observable;

public interface Model {
    /*Яндекс.Переводчик*/
    Observable<AvailableLanguagesDTO> getAvailableLanguages(String forLanguage);
    Observable<ProbableLanguageDTO> getProbableLanguage(String forText);
    Observable<TranslationDTO> getTranslation(String forText, String direction);

    /*Яндекс.Словарь*/
    Observable<DicResultDTO> getDicResult(String forLanguage, String text, String forUi);
}
