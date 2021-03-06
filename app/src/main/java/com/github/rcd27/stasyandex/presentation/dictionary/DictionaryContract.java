package com.github.rcd27.stasyandex.presentation.dictionary;

import com.github.rcd27.stasyandex.model.data.dictionary.*;
import com.github.rcd27.stasyandex.presentation.*;
import com.github.rcd27.stasyandex.presentation.dictionary.visual.*;

import java.util.*;

import io.reactivex.*;
import retrofit2.*;
import retrofit2.http.*;

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
    Single<Response<DictionaryResponse>> getDicResultFor(@Query("lang") String languageDirection,
                                                         @Query("text") String text,
                                                         @Query("ui") String inLanguage);
  }
}
