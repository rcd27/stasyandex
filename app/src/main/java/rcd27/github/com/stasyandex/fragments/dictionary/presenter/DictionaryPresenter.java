package rcd27.github.com.stasyandex.fragments.dictionary.presenter;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import rcd27.github.com.stasyandex.fragments.dictionary.presenter.vo.DictionaryDefinition;
import rcd27.github.com.stasyandex.fragments.dictionary.presenter.vo.DictionaryItem;
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
        addSubscriprtion(getSubscribtionForDictionary(text));
    }

    //TODO хорошо, допустим это работает. Но это мрак с повторяющимся кодом! см.ModelImpl
    private Subscription getSubscribtionForDictionary(String text) {
        return responseData.getDicResult("ru-en", text, "ru")
                .flatMap(dicRes -> Observable.from(dicRes.getDef()))
                //TODO вот тут вполне можно вшить Observable.zip
                .flatMap(dicDef -> Observable.just(new DictionaryDefinition(dicDef.getText(), dicDef.getPos())))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dicDef -> view.showDictionaryDefiniton(dicDef));
    }

    private void showFakeVO() {
        DictionaryDefinition fakeDef = new DictionaryDefinition("привет", "сущ");
        view.showDictionaryDefiniton(fakeDef);

        List<String> fakeSynListItemOne = new ArrayList<>();
        fakeSynListItemOne.add("hello");
        fakeSynListItemOne.add("salutation");
        fakeSynListItemOne.add("hallo");
        List<String> fakeMeanList = new ArrayList<>();
        fakeMeanList.add("приветствие");
        DictionaryItem fakeItemOne = new DictionaryItem("hi",
                fakeSynListItemOne, fakeMeanList);

        List<String> fakeSynListItemTwo = new ArrayList<>();
        List<String> fakeMeanListTwo = new ArrayList<>();
        fakeMeanListTwo.add("ну");
        fakeMeanListTwo.add("проверка запятой");
        DictionaryItem fakeItemTwo = new DictionaryItem("hey",
                fakeSynListItemTwo, fakeMeanListTwo);

        List<DictionaryItem> fakeItemList = new ArrayList<>();
        fakeItemList.add(fakeItemOne);
        fakeItemList.add(fakeItemTwo);
        view.showDictionaryDictionaryItems(fakeItemList);
    }
}
