package rcd27.github.com.stasyandex.fragments.dictionary.presenter;


import android.util.Log;

import rcd27.github.com.stasyandex.fragments.dictionary.model.dto.Def;
import rcd27.github.com.stasyandex.fragments.dictionary.view.DictionaryView;
import rcd27.github.com.stasyandex.presenter.BasePresenter;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DictionaryPresenter extends BasePresenter {

    private final String TAG = getClass().getSimpleName();

    private DictionaryView view;

    public DictionaryPresenter(DictionaryView view) {
        this.view = view;
    }

    public void onGetDictionaryResponse() {
        String text = view.getDictionaryFor();
        addSubscription(getSubscriptionForDictionaryDefention(text));
    }

    //оставляю идею смапить всё по-красивому до лучших времён. Отображаться будет DTO
    private Subscription getSubscriptionForDictionaryDefention(String text) {
        return responseData.getDicResult("ru-en", text, "ru")
                .flatMap(response -> Observable.from(response.getDef()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toSingle()
                .subscribe(new Observer<Def>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "subscription: onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.w(TAG, "subscription: onError()");
                    }

                    @Override
                    public void onNext(Def def) {
                        view.showDef(def);
                        Log.i(TAG, "subscription: onNext()");
                    }
                });
    }
}
