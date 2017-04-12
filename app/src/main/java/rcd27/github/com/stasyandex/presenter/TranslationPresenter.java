package rcd27.github.com.stasyandex.presenter;


import android.text.TextUtils;
import android.util.Log;

import java.util.Arrays;
import java.util.List;

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
        if (TextUtils.isEmpty(textToTranslate) || textToTranslate.isEmpty()) {
            view.showError("Введите текст для перевода.");
            view.showEmpty();
            return;
        }
        showFakeVO();
    }

    private void showFakeVO() {
        List<String> fakeResult = Arrays.asList("Фэйковый перевод тут, ага.");
        translation = new Translation(fakeResult);
        view.showTranslation(translation);
    }

    private void getTranslationWithSubscription(String textToTranslate) {
        Subscription subscription = responseData.getTranslation(textToTranslate, "en")
                .map(translationMapper)
                .subscribe(new Observer<Translation>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "subscription: onCompleted()");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.w(TAG, "subscriprion: onError()");
                    }

                    @Override
                    public void onNext(Translation response) {
                        if (null != response && !response.isEmpty()) {
                            translation = response;
                            view.showTranslation(translation);
                            Log.i(TAG, "response from server is OK");
                        } else {
                            view.showEmpty();
                            Log.w(TAG, "response from server is INVALID");
                        }
                    }
                });
        addSubscriprtion(subscription);
    }
}
