package rcd27.github.com.stasyandex.model.translation;


import rcd27.github.com.stasyandex.model.translation.dto.AvailableLanguages;
import rcd27.github.com.stasyandex.model.translation.dto.ProbableLanguage;
import rcd27.github.com.stasyandex.model.translation.dto.Translation;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface TranslationAPI {
    @GET("api/v1.5/tr.json/getLangs")
    Observable<AvailableLanguages> getAvailableLangs(@Query("ui") String forLanguage);

    @GET("api/v1.5/tr.json/detect")
    Observable<ProbableLanguage> getProbableLanguage(@Query("text") String text);

    @GET("api/v1.5/tr.json/translate")
    Observable<Translation> getTranslation(@Query("text") String textToTranslate,
                                           @Query("lang") String translationDirection);
}
