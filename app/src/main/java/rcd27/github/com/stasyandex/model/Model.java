package rcd27.github.com.stasyandex.model;


import rcd27.github.com.stasyandex.model.dictionary.DicResultDTO;
import rcd27.github.com.stasyandex.model.translation.AvailableLanguagesDTO;
import rcd27.github.com.stasyandex.model.translation.ProbableLanguageDTO;
import rcd27.github.com.stasyandex.model.translation.TranslationDTO;
import rx.Observable;

public interface Model {
    /*Яндекс.Переводчик*/
    Observable<AvailableLanguagesDTO> getAvailableLanguages(String forLanguage);
    Observable<ProbableLanguageDTO> getProbableLanguage(String forText);
    Observable<TranslationDTO> getTranslation(String forText, String direction);

    /*Яндекс.Словарь
    @forLanguage - "ru-en"
    @text - "привет"
    @forUi - "ru"
     */
    Observable<DicResultDTO> getDicResult(String forLanguage, String text, String forUi);
}
