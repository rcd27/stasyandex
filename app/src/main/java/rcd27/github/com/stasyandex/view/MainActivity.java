package rcd27.github.com.stasyandex.view;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import rcd27.github.com.stasyandex.R;
import rcd27.github.com.stasyandex.view.dictionary.DictionaryFragment;
import rcd27.github.com.stasyandex.view.translation.TranslationFragment;

public class MainActivity extends AppCompatActivity implements TranslationFragment.TranslateButtonListener {

    //TODO: Поддержка выбора языков.

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
    public void onTranslateEditTextChanged(String textFromEditText) {
        DictionaryFragment dicFrag = (DictionaryFragment) fragmentManager
                .findFragmentById(R.id.dictionary_fragment);
        dicFrag.getPresenter().onGetDictionaryResponse(textFromEditText);
    }
}
