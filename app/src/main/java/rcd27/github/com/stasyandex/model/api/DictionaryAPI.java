package rcd27.github.com.stasyandex.model.api;


import rcd27.github.com.stasyandex.model.dto.dictionary.DicResultDTO;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface DictionaryAPI {
    //TODO прикрутить возможность получать укороченную pos(часть речи)
    //https://tech.yandex.ru/dictionary/doc/dg/reference/lookup-docpage/
    @GET("api/v1/dicservice.json/lookup")
    Observable<DicResultDTO> getDicResultFor(@Query("lang") String language,
                                             @Query("text") String text,
                                             @Query("ui") String inLanguage);
}
