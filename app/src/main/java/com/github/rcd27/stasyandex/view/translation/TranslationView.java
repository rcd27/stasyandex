package com.github.rcd27.stasyandex.view.translation;


import com.github.rcd27.stasyandex.model.translation.dto.Translation;
import com.github.rcd27.stasyandex.view.BaseView;

public interface TranslationView extends BaseView {

    /*Что переводим. Текст из поля ввода*/
    String getTextFromEditText();

    /*Отображаем результат перевода*/
    void showTranslation(Translation translation);

    /*Выбор языка*/
    void chooseLanguage(final int direction);

    //TODO FIXME вот тут много делов. Выглядит громоздко. Объеденить методы
    /*Показать с какого языка переводим*/
    void showLanguageFrom(String selectedLanguage);

    /*На какой*/
    void showLanguageTo(String selectedLanguage);

    /*Взять значение из tv переводимого языка*/
    String getLanguageFrom();

    /*В какой*/
    String getLanguageTo();

    /*Переход к истории*/
    void openHistory();

    void clearEditText();
}
