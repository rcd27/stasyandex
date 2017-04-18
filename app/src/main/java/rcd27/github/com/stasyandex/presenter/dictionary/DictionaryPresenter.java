package rcd27.github.com.stasyandex.presenter.dictionary;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import rcd27.github.com.stasyandex.model.dictionary.dto.DefinitionDTO;
import rcd27.github.com.stasyandex.model.dictionary.dto.DicTranslationDTO;
import rcd27.github.com.stasyandex.model.dictionary.dto.MeaninigDTO;
import rcd27.github.com.stasyandex.model.dictionary.dto.SynonymDTO;
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
        /* NULL Object from Retrofit
        addSubscription(getSubscriptionForDictionaryDefention(text));
        */
        showFake();
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

    //TODO потом это перейдёт в тесты.
    private void showFake() {
        DefinitionDTO fakeDTO = new DefinitionDTO();

        DicTranslationDTO dicTransDTO = new DicTranslationDTO();
        dicTransDTO.setText("фальшивый");
        dicTransDTO.setPos("перевод");

        List<MeaninigDTO> fakeMeaningList = new ArrayList<>();
        MeaninigDTO fakeMeaning = new MeaninigDTO();
        fakeMeaning.setText("значение");
        fakeMeaningList.add(fakeMeaning);
        dicTransDTO.setMeaninigDTO(fakeMeaningList);

        List<SynonymDTO> fakeSynList = new ArrayList<>();
        SynonymDTO fakeSynOne = new SynonymDTO();
        fakeSynOne.setText("синоним1");
        SynonymDTO fakeSynTwo = new SynonymDTO();
        fakeSynTwo.setText("синоним2");
        SynonymDTO fakeSynThree = new SynonymDTO();
        fakeSynThree.setText("синоним3");
        fakeSynList.add(fakeSynOne);
        fakeSynList.add(fakeSynTwo);
        fakeSynList.add(fakeSynThree);
        dicTransDTO.setSynonymDTO(fakeSynList);

        DicTranslationDTO dicTransDTO2 = new DicTranslationDTO();
        dicTransDTO2.setText("и");
        dicTransDTO2.setPos("тут");

        List<MeaninigDTO> fakeMeaningList2 = new ArrayList<>();
        MeaninigDTO fakeMeaning2 = new MeaninigDTO();
        fakeMeaning2.setText("значение2");
        fakeMeaningList2.add(fakeMeaning2);
        dicTransDTO2.setMeaninigDTO(fakeMeaningList2);

        List<SynonymDTO> fakeSynList2 = new ArrayList<>();
        SynonymDTO fakeSynOne2 = new SynonymDTO();
        fakeSynOne2.setText("синоним4");
        SynonymDTO fakeSynTwo2 = new SynonymDTO();
        fakeSynTwo2.setText("синоним5");
        SynonymDTO fakeSynThree2 = new SynonymDTO();
        fakeSynThree2.setText("синоним6");
        fakeSynList2.add(fakeSynOne2);
        fakeSynList2.add(fakeSynTwo2);
        fakeSynList2.add(fakeSynThree2);
        dicTransDTO2.setSynonymDTO(fakeSynList2);


        List<DicTranslationDTO> fakeDicTransDTOList = new ArrayList<>();
        fakeDicTransDTOList.add(dicTransDTO);
        fakeDicTransDTOList.add(dicTransDTO2);
        fakeDTO.setDicTranslationDTO(fakeDicTransDTOList);

        view.showDef(fakeDTO);
    }
}
