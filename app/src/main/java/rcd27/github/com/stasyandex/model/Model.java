package rcd27.github.com.stasyandex.model;


import rcd27.github.com.stasyandex.fragments.dictionary.model.dto.DicResultDTO;
import rcd27.github.com.stasyandex.fragments.translation.model.dto.AvailableLanguagesDTO;
import rcd27.github.com.stasyandex.fragments.translation.model.dto.ProbableLanguageDTO;
import rcd27.github.com.stasyandex.fragments.translation.model.dto.TranslationDTO;
import rx.Observable;

public interface Model {
    /*Яндекс.Переводчик*/
    Observable<AvailableLanguagesDTO> getAvailableLanguages(String forLanguage);
    Observable<ProbableLanguageDTO> getProbableLanguage(String forText);
    Observable<TranslationDTO> getTranslation(String forText, String direction);

    /*Яндекс.Словар
    @forLanguage - "ru-en"
    @text - "привет"
    @forUi - "ru"
     */
    Observable<DicResultDTO> getDicResult(String forLanguage, String text, String forUi);
}
