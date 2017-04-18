package rcd27.github.com.stasyandex;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import rcd27.github.com.stasyandex.presenter.visualobject.DictionaryDefinition;
import rcd27.github.com.stasyandex.view.dictionary.DictionaryFragment;
import rcd27.github.com.stasyandex.view.translation.TranslationFragment;

// 14.04.2017
//TODO Найти этот блин динамически созданный фрагмент!

public class MainActivity extends AppCompatActivity implements TranslationFragment.Listener {

//  Использовать Observable для обращения к БД
//  Исползовать Observable для чтения текста из EditText. Прикрутить задержку ~500мс.
//  Юзер ввёл текст - 500мс - отправился запрос на сервак.

    //Общий план работ:
    // •Прикрутить выбор языка
    // •Выбранный язык должен влиять на всю логику перевода/словаря
    // •Выбранное направление должно влиять на логику словаря
    // •Все успешные переводы (придумать критерий) сохранять в историю
    // •Добавить флажок "Избранное"
    // •Добавить закладки История/Избранное (суть - сортировка истории по флажку "Избранное")
    // •Запрос на сервер отсылается 500мс после неактивности в поле ввода
    // •Клавиатура должна убираться после успешного запроса
    // •Запрос к словарю и к переводчику должны идти одновременно

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onTranslateButtonClicked(String textFromEditText) {
        DictionaryFragment dicFrag = (DictionaryFragment) fragmentManager
                .findFragmentById(R.id.dictionary_fragment);
        dicFrag.getPresenter().onGetDictionaryResponse(textFromEditText);
    }
}
