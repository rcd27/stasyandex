package rcd27.github.com.stasyandex.presenter.dictionary;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rcd27.github.com.stasyandex.model.dictionary.dto.Definition;
import rcd27.github.com.stasyandex.model.dictionary.dto.DicTranslation;
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

    @Inject
    public DictionaryPresenter() {
    }

    public DictionaryPresenter(DictionaryView view) {
        super();
        this.view = view;
    }

    public void onGetDictionaryResponse(String text) {
//        addSubscription(getSubscriptionForDictionaryDefention(text));
        showFake();
    }

    //TODO вот тут конкретные проблемы.
    private Subscription getSubscriptionForDictionaryDefention(String text) {
        return responseData.getDicResult("ru-en", text, "ru")
                .flatMap(response -> Observable.from(response.getDefinition()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toSingle()
                .subscribe(new Observer<Definition>() {
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
                    public void onNext(Definition definition) {
                        view.showDef(definition);
                        Log.i(TAG, "subscription: onNext()");
                    }
                });
    }

    //TODO убрать.
    private void showFake() {
        Definition fakeDTO = new Definition();

        DicTranslation dicTransDTO = new DicTranslation();
        dicTransDTO.setText("фальшивый");
        dicTransDTO.setPos("перевод");

        List<String> fakeMeaningList = new ArrayList<>();
        String fakeMeaning = "(значение)";
        fakeMeaningList.add(fakeMeaning);
        dicTransDTO.setMeanings(fakeMeaningList);

        List<String> fakeSynList = new ArrayList<>();
        fakeSynList.add("синоним1");
        fakeSynList.add("синоним2");
        fakeSynList.add("синоним3");
        dicTransDTO.setSynonyms(fakeSynList);

        DicTranslation dicTransDTO2 = new DicTranslation();
        dicTransDTO2.setText("и");
        dicTransDTO2.setPos("тут");

        List<String> fakeMeaningList2 = new ArrayList<>();
        String fakeMeaning2 = "(значение2)";
        fakeMeaningList2.add(fakeMeaning2);
        dicTransDTO2.setMeanings(fakeMeaningList2);

        List<String> fakeSynList2 = new ArrayList<>();
        fakeSynList2.add("синоним4");
        fakeSynList2.add("синоним5");
        fakeSynList2.add("синоним6");
        dicTransDTO2.setSynonyms(fakeSynList2);


        List<DicTranslation> fakeDicTransDTOList = new ArrayList<>();
        fakeDicTransDTOList.add(dicTransDTO);
        fakeDicTransDTOList.add(dicTransDTO2);
        fakeDTO.setDicTranslation(fakeDicTransDTOList);

//        List<Definition> koko = new ArrayList<>();
//        koko.add(fakeDTO);
        view.showDef(fakeDTO);
    }
}
