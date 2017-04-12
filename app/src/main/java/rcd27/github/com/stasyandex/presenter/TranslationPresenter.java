package rcd27.github.com.stasyandex.presenter;


import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import rcd27.github.com.stasyandex.presenter.mappers.TranslationMapper;
import rcd27.github.com.stasyandex.presenter.visualobjects.Translation;
import rcd27.github.com.stasyandex.view.fragments.TranslationView;
import rx.Observer;
import rx.Subscription;

import static rcd27.github.com.stasyandex.Constant.METHOD_INVOCATION;

public class TranslationPresenter extends BasePresenter {

    private final String TAG = this.getClass().getSimpleName();

    private TranslationView view;

    private TranslationMapper translationMapper = new TranslationMapper();
    private Translation translation;

    public TranslationPresenter(TranslationView view) {
        this.view = view;
    }

    public void onGetTranslation() {
        Log.i(TAG, METHOD_INVOCATION.value + "onGetTranslation()");
        String textToTranslate = view.getTextFromEditText();
        Log.i(TAG, "text to translate: " + textToTranslate);
        if (TextUtils.isEmpty(textToTranslate)) {
            view.showError("Введите текст для перевода.");
            return;
        }

        Subscription subscription = responseData.getTranslation(textToTranslate, "en")
                .map(translationMapper)
                .subscribe(new Observer<Translation>() {
                    @Override
                    public void onCompleted() {
                        //empty
                    }

                    @Override
                    public void onError(Throwable e) {
                        //empty
                    }

                    @Override
                    public void onNext(Translation response) {
                        if (null != response && !response.isEmpty()) {
                            translation = response;
                            view.showTranslation(translation.getTranslationResult());
                            Log.i("TranslationPresenter:", "response from server is OK");
                        } else {
                            view.showEmpty();
                            Log.w("TranslationPresenter:", "response from server is INVALID");
                        }
                    }
                });
        addSubscriprtion(subscription);
    }

    public void onCreate(Bundle savedInstanceState) {
        //empty
    }

    public void onSaveInstanceState(Bundle outState) {
        //empty
    }
}
