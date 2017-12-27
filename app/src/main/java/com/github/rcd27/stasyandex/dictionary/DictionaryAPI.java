package com.github.rcd27.stasyandex.dictionary;


import com.github.rcd27.stasyandex.data.dictionary.DicResult;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface DictionaryAPI {
    //TODO прикрутить возможность получать укороченную pos(часть речи)
    //https://tech.yandex.ru/dictionary/doc/dg/reference/lookup-docpage/
    @GET("api/v1/dicservice.json/lookup")
    Observable<DicResult> getDicResultFor(@Query("lang") String languageDirection,
                                          @Query("text") String text,
                                          @Query("ui") String inLanguage);
}
