package com.github.rcd27.stasyandex;


import com.github.rcd27.stasyandex.data.dictionary.DicResult;
import com.github.rcd27.stasyandex.data.translation.AvailableLanguages;
import com.github.rcd27.stasyandex.data.translation.ProbableLanguage;
import com.github.rcd27.stasyandex.data.translation.Translation;
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
