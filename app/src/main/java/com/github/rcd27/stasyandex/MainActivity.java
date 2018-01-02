package com.github.rcd27.stasyandex;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.github.rcd27.stasyandex.R;
import com.github.rcd27.stasyandex.dictionary.DictionaryFragment;
import com.github.rcd27.stasyandex.translation.TranslationFragment;

public class MainActivity extends AppCompatActivity implements TranslationFragment.TranslateButtonListener {

    //MAIN TODO:
    //      2)Написать тесты
    //      3)Дописать весь функционал (с соотв. тестами)

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        //TODO пробросить getCacheDir() в OkHttpClient;
    }

    @Override
    public void onTranslateEditTextChanged(String direction, String textFromEditText) {
        DictionaryFragment dicFrag = (DictionaryFragment) fragmentManager
                .findFragmentById(R.id.dictionary_fragment);
        //TODO добавить интерсептор для okhttp3,чтобы тот ловил ошибки от сервера при
        //неподдерживаемом языке.
        dicFrag.getPresenter().getDictionaryResponseFor("ru-en", textFromEditText);
    }
}
