package rcd27.github.com.stasyandex.view.fragments;


import java.util.List;

public interface TranslationView extends View {

    /*Что переводим. Текст из поля ввода*/
    String getTextFromEditText();

    /*Отображаем результат перевода*/
    void showTranslation(List<String> translationsToShow);

    /*Показать пустой результат/очистка поля результата перевода*/
    void showEmpty();
}
