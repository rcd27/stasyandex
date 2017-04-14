package rcd27.github.com.stasyandex;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import rcd27.github.com.stasyandex.fragments.favourites.view.FavouritesFragment;
import rcd27.github.com.stasyandex.fragments.translation.view.TranslationFragment;

// 14.04.2017
//TODO MAIN: прикрутить API словаря.
//  •пора прикручивать мокиту, я так думаю
//  •сделать качественный маппинг DTO→VO

// 15.04.2017
//TODO MAIN: реализовать AvailableLanguages в меню окна перевода.
public class MainActivity extends AppCompatActivity implements ActivityCallback,
TranslationFragment.Listener{

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

    private static String TAG = "TAG";

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (null != findViewById(R.id.fragment_container)) {
            if (null != savedInstanceState) {
                return;
            }
        }
        fragmentManager = getSupportFragmentManager();

        Fragment fragment = fragmentManager.findFragmentByTag(TAG);
        if (null == fragment) {
            replaceFragment(new TranslationFragment(), false);
        }
    }

    private void replaceFragment(Fragment fragment, boolean addBackStack) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment, TAG);
        if (addBackStack) {
            transaction.addToBackStack(null); //TODO почему null?
        }
        transaction.commit();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void startFavouritesFragment() {
        replaceFragment(FavouritesFragment.newInstance(), true);
    }

    /*Фрагмент с словарём встроен в переводчик.*/
    @Override
    public void startTranslationFragment() {
        replaceFragment(TranslationFragment.newInstance(), true);
    }

    @Override
    public void onTranslateButtonClicked(String textFromEditText) {
        //TODO ТУТ НАДО НАЙТИ ФРАГМЕНТ СЛОВАРЯ И БАХНУТЬ В НЕГО textFromEditText
    }
}
