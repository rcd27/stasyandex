package rcd27.github.com.stasyandex.fragments.translation.view;


import rcd27.github.com.stasyandex.fragments.translation.presenter.vo.Translation;
import rcd27.github.com.stasyandex.view.BaseView;

public interface TranslationView extends BaseView {

    /*Что переводим. Текст из поля ввода*/
    String getTextFromEditText();

    /*Отображаем результат перевода*/
    void showTranslation(Translation translation);

    /*Показать пустой результат/очистка поля результата перевода*/
    void showEmpty();
}
