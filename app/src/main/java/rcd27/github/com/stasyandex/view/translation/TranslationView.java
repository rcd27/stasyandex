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
}
