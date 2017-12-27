package com.github.rcd27.stasyandex.translation;


import com.github.rcd27.stasyandex.data.translation.AvailableLanguages;
import com.github.rcd27.stasyandex.data.translation.ProbableLanguage;
import com.github.rcd27.stasyandex.data.translation.Translation;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface TranslationAPI {
    @GET("api/v1.5/tr.json/getLangs")
    //TODO: Поменять Observable<> на Single<>
    //см. Single vs Observable: в случае, когда имеем один объект - снгл лучше
    Observable<AvailableLanguages> getAvailableLangs(@Query("ui") String forLanguage);

    @GET("api/v1.5/tr.json/detect")
    Observable<ProbableLanguage> getProbableLanguage(@Query("text") String text);

    @GET("api/v1.5/tr.json/translate")
    Observable<Translation> getTranslation(@Query("text") String textToTranslate,
                                           @Query("lang") String translationDirection);
}
