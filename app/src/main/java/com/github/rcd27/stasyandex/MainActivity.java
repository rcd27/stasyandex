package com.github.rcd27.stasyandex;

import android.os.*;
import android.support.v4.app.*;
import android.support.v7.app.*;

import com.github.rcd27.stasyandex.presentation.dictionary.*;
import com.github.rcd27.stasyandex.presentation.translation.*;

public class MainActivity extends AppCompatActivity implements TranslationFragment.TranslateButtonListener {

  private FragmentManager fragmentManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    fragmentManager = getSupportFragmentManager();
    //TODO пробросить getCacheDir() в OkHttpClient;
  }

  public void onTranslateEditTextChanged(String direction, String textFromEditText) {
    DictionaryFragment dicFrag = (DictionaryFragment) fragmentManager
        .findFragmentById(R.id.dictionary_fragment);
    //TODO добавить интерсептор для okhttp3,чтобы тот ловил ошибки от сервера при
    //неподдерживаемом языке.
    dicFrag.getPresenter().getDictionaryResponseFor("ru-en", textFromEditText);
  }
}
