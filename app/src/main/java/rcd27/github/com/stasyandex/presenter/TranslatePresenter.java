package rcd27.github.com.stasyandex.presenter;


import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import rcd27.github.com.stasyandex.model.data.Translation;
import rcd27.github.com.stasyandex.view.fragments.TranslationView;

import static rcd27.github.com.stasyandex.Constant.METHOD_INVOCATION;

public class TranslatePresenter extends BasePresenter {

    private final String TAG = this.getClass().getSimpleName();

    private static final String BUNDLE_TRANSLATE_KEY = "BUNDLE_TRANSLATE_KEY";

    private TranslationView view;

    private List<Translation> translationList;

    private String originWord = "Origin ёптыть";

    public TranslatePresenter(TranslationView view) {
        this.view = view;
    }

    public void onGetTranslation() {
        Log.i(TAG, METHOD_INVOCATION.value + "onGetTranslation()");
        //TODO тут вся логика по получению перевода, подписке и пр.
        //TODO присвоить origin'u значение
    }

    public void onCreate(Bundle savedInstanceState) {
        if (null != savedInstanceState) {
            translationList = (List<Translation>) savedInstanceState.getSerializable(BUNDLE_TRANSLATE_KEY);
        }

        if (!translationListIsEmpty()) {
            view.showTranslation(translationList);
        }
    }

    private boolean translationListIsEmpty() {
        return translationList == null || translationList.isEmpty();
    }

    public void onSaveInstanceState(Bundle outState) {
        if (!translationListIsEmpty()) {
            outState.putSerializable(BUNDLE_TRANSLATE_KEY, new ArrayList<>(translationList));
        }
    }

    public String getOriginWord() {
        return originWord;
    }
}
