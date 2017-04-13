package rcd27.github.com.stasyandex.fragments.dictionary.presenter;


import rcd27.github.com.stasyandex.fragments.dictionary.presenter.vo.DictionaryDefinition;
import rcd27.github.com.stasyandex.fragments.dictionary.view.DictionaryView;
import rcd27.github.com.stasyandex.presenter.BasePresenter;
import rx.Observable;
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

    //TODO хорошо, допустим это работает. Но это мрак с повторяющимся кодом! см.ModelImpl
    private Subscription getSubscriptionForDictionaryDefention(String text) {
        return responseData.getDicResult("ru-en", text, "ru")
                .flatMap(dicRes -> Observable.from(dicRes.getDef()))
                //TODO вот тут вполне можно вшить Observable.zip
                .flatMap(dicDef -> Observable.just(new DictionaryDefinition(dicDef.getText(), dicDef.getPos())))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dicDef -> view.showDictionaryDefiniton(dicDef));
    }
}
