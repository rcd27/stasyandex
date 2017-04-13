package rcd27.github.com.stasyandex;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import rcd27.github.com.stasyandex.fragments.favourites.view.FavouritesFragment;
import rcd27.github.com.stasyandex.fragments.translation.view.TranslationFragment;

//TODO MAIN: восстановить работоспособность переводчика. Ошибка в Subscription.
//TODO MAIN: прикрутить API словаря.
//  •для начала отобразить в списке просто фэйковый vo ←
//  •сделать качественный маппинг DTO→VO
//TODO MAIN: реализовать AvailableLanguages в меню окна перевода.
public class MainActivity extends AppCompatActivity implements ActivityCallback {

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
}
