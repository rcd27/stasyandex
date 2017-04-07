package rcd27.github.com.stasyandex.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import rcd27.github.com.stasyandex.R;
import rcd27.github.com.stasyandex.view.fragments.FavouritesFragment;
import rcd27.github.com.stasyandex.view.fragments.TranslationFragment;

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

    @Override
    public void startTranslationFragment() {
        replaceFragment(TranslationFragment.newInstance(), true);
    }
}
