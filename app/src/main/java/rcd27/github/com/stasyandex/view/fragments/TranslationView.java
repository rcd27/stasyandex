package rcd27.github.com.stasyandex.view.fragments;


import rcd27.github.com.stasyandex.presenter.visualobjects.Translation;

public interface TranslationView extends View {

    /*Что переводим. Текст из поля ввода*/
    String getTextFromEditText();

    /*Отображаем результат перевода*/
    void showTranslation(Translation translation);

    /*Показать пустой результат/очистка поля результата перевода*/
    void showEmpty();
}
