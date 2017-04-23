package rcd27.github.com.stasyandex.view.translation;


import rcd27.github.com.stasyandex.presenter.visualobject.Translation;
import rcd27.github.com.stasyandex.view.BaseView;

public interface TranslationView extends BaseView {

    /*Что переводим. Текст из поля ввода*/
    String getTextFromEditText();

    /*Отображаем результат перевода*/
    void showTranslation(Translation translation);

    /*Показать пустой результат/очистка поля результата перевода*/
    void showEmptyResut();

    /*Выбор языка*/
    void chooseLanguage(final int direction);

    /*Показать с какого языка переводим*/
    void showLanguageFrom(String selectedLAnguage);

    /*На какой*/
    void showLanguageTo(String selectedLAnguage);
}
