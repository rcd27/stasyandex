package com.github.rcd27.stasyandex.model;


import com.github.rcd27.stasyandex.model.dictionary.dto.DicResult;
import com.github.rcd27.stasyandex.model.translation.dto.AvailableLanguages;
import com.github.rcd27.stasyandex.model.translation.dto.ProbableLanguage;
import com.github.rcd27.stasyandex.model.translation.dto.Translation;
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
    Observable<DicResult> getDicResult(String forLanguage, String text, String forUi);
}
