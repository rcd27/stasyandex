package rcd27.github.com.stasyandex.model;


import rcd27.github.com.stasyandex.model.dictionary.dto.DicResultDTO;
import rcd27.github.com.stasyandex.model.translation.dto.AvailableLanguages;
import rcd27.github.com.stasyandex.model.translation.dto.ProbableLanguage;
import rcd27.github.com.stasyandex.model.translation.dto.Translation;
import rx.Observable;

public interface Model {
    /*Яндекс.Переводчик*/
    Observable<AvailableLanguages> getAvailableLanguages(String forLanguage);
    Observable<ProbableLanguage> getProbableLanguage(String forText);
    Observable<Translation> getTranslation(String forText, String direction);

    /*Яндекс.Словарь
    @forLanguage - "ru-en"
    @text - "привет"
    @forUi - "ru"
     */
    Observable<DicResultDTO> getDicResult(String forLanguage, String text, String forUi);
}
