package rcd27.github.com.stasyandex.fragments.dictionary.presenter;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import rcd27.github.com.stasyandex.fragments.dictionary.model.dto.DicResultDTO;
import rcd27.github.com.stasyandex.fragments.dictionary.presenter.vo.DictionaryDefinition;
import rcd27.github.com.stasyandex.fragments.dictionary.presenter.vo.DictionaryItem;
import rcd27.github.com.stasyandex.fragments.dictionary.view.DictionaryView;
import rcd27.github.com.stasyandex.presenter.BasePresenter;
import rx.Observer;
import rx.Subscription;

public class DictionaryPresenter extends BasePresenter {

    private final String TAG = getClass().getSimpleName();

    private DictionaryView view;

    public DictionaryPresenter(DictionaryView view) {
        this.view = view;
    }

    public void onGetDictionaryResponse() {
        String text = view.getDictionaryFor();
        //проверки на пустой текст не будет - передаваться сюда должен из TranslationPresenter
//        addSubscriprtion(getSubscribtionForDictionary(text));
        showFakeVO();
    }

    private Subscription getSubscribtionForDictionary(String text) {
        return responseData.getDicResult("ru", text, "ru")
                //ясен красен надо мапперами всё затыкать там, чтобы красиво в итоге было
                .subscribe(new Observer<DicResultDTO>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "subscription: onCompleted()");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "subscription: onError()");
                    }

                    @Override
                    public void onNext(DicResultDTO dicResultDTO) {
                        Log.i(TAG, "subscription: onNext()");
                    }
                });
    }

    private void showFakeVO() {
        DictionaryDefinition fakeDef = new DictionaryDefinition("привет", "сущ");
        List<DictionaryDefinition> fakeDefList = new ArrayList<>();
        fakeDefList.add(fakeDef);
        view.showDictionaryDefiniton(fakeDefList);

        List<String> fakeSynListItemOne = new ArrayList<>();
        fakeSynListItemOne.add("hello");
        fakeSynListItemOne.add("salutation");
        fakeSynListItemOne.add("hallo");
        List<String> fakeMeanList = new ArrayList<>();
        fakeMeanList.add("приветствие");
        DictionaryItem fakeItemOne = new DictionaryItem("hi",
                fakeSynListItemOne, "сущ", fakeMeanList);

        List<String> fakeSynListItemTwo = new ArrayList<>();
        List<String> fakeMeanListTwo = new ArrayList<>();
        fakeMeanListTwo.add("ну");
        fakeMeanListTwo.add("проверка запятой");
        DictionaryItem fakeItemTwo = new DictionaryItem("hey",
                fakeSynListItemTwo, "сущ", fakeMeanListTwo);

        List<DictionaryItem> fakeItemList = new ArrayList<>();
        fakeItemList.add(fakeItemOne);
        fakeItemList.add(fakeItemTwo);
        view.showDictionaryDictionaryItems(fakeItemList);
    }
}
