package rcd27.github.com.stasyandex.presenter.dictionary;


import javax.inject.Inject;

import rcd27.github.com.stasyandex.model.dictionary.dto.DicResult;
import rcd27.github.com.stasyandex.presenter.BasePresenter;
import rcd27.github.com.stasyandex.view.dictionary.DictionaryView;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DictionaryPresenter extends BasePresenter {

    private final String TAG = getClass().getSimpleName();

    private DictionaryView view;

    @Inject
    public DictionaryPresenter() {
    }

    public DictionaryPresenter(DictionaryView view) {
        super();
        this.view = view;
    }

    public void onGetDictionaryResponse(String text) {
        addSubscription(getSubscriptionForDictionaryDefention(text));
    }

    private Subscription getSubscriptionForDictionaryDefention(String text) {
        return responseData.getDicResult("ru-en", text, "ru")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .firstOrDefault(new DicResult())
                .doOnNext(dicResult -> {
                    if (!dicResult.definitionListIsEmptyOrNull()) {
                        view.showDefinition(dicResult.getDefinition());
                        view.showDictionaryItems(dicResult.getElementsList());
                    } else {
                        view.showEmpty();
                    }
                })
                .subscribe();
    }
}
