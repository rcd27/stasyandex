package rcd27.github.com.stasyandex.presenter.dictionary;


import android.util.Log;

import rcd27.github.com.stasyandex.model.dictionary.dto.DefinitionDTO;
import rcd27.github.com.stasyandex.presenter.BasePresenter;
import rcd27.github.com.stasyandex.view.dictionary.DictionaryView;
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

    public void onGetDictionaryResponse(String text) {
        addSubscription(getSubscriptionForDictionaryDefention(text));
    }

    //оставляю идею смапить всё по-красивому до лучших времён. Отображаться будет DTO
    private Subscription getSubscriptionForDictionaryDefention(String text) {
        return responseData.getDicResult("ru-en", text, "ru")
                .flatMap(response -> Observable.from(response.getDefinitionDTO()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toSingle()
                .subscribe(new Observer<DefinitionDTO>() {
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
                    public void onNext(DefinitionDTO definitionDTO) {
                        view.showDef(definitionDTO);
                        Log.i(TAG, "subscription: onNext()");
                    }
                });
    }
}
