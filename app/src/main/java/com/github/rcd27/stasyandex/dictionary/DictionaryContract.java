package com.github.rcd27.stasyandex.dictionary;

import com.github.rcd27.stasyandex.common.BaseView;
import com.github.rcd27.stasyandex.data.dictionary.DicResult;
import com.github.rcd27.stasyandex.dictionary.visual.DictionaryVisualDefinition;
import com.github.rcd27.stasyandex.dictionary.visual.DictionaryVisualItem;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Single;

public interface DictionaryContract {

    interface View extends BaseView {

        void showDefinition(DictionaryVisualDefinition definition);

        void showDictionaryItems(List<DictionaryVisualItem> items);
    }

    interface Presenter {

    }

    interface Api {
        //TODO прикрутить возможность получать укороченную pos(часть речи)
        //https://tech.yandex.ru/dictionary/doc/dg/reference/lookup-docpage/
        @GET("api/v1/dicservice.json/lookup")
        Single<DicResult> getDicResultFor(@Query("lang") String languageDirection,
                                          @Query("text") String text,
                                          @Query("ui") String inLanguage);
    }
}
