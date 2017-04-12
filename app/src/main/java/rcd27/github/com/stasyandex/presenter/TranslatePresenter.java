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

public class TranslatePresenter extends BasePresenter {

    private final String TAG = this.getClass().getSimpleName();

    private TranslationView view;

    private TranslationMapper translationMapper = new TranslationMapper();
    private Translation translation;

    public TranslatePresenter(TranslationView view) {
        this.view = view;
    }

    public void onGetTranslation() {
        Log.i(TAG, METHOD_INVOCATION.value + "onGetTranslation()");
        String textToTranslate = view.getTranslationFor();
        if (TextUtils.isEmpty(textToTranslate)) {
            //TODO сделать всплывающее окно о пустом запросе
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
                        } else {
                            view.showEmpty();
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
