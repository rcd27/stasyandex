package rcd27.github.com.stasyandex.model.api;


import rcd27.github.com.stasyandex.model.data.AvailableLanguages;
import rcd27.github.com.stasyandex.model.data.ProbableLanguage;
import rcd27.github.com.stasyandex.model.data.Translation;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiInterface {
    @GET("api/v1.5/tr.json/getLangs")
    Observable<AvailableLanguages> getAvailableLangs(@Query("ui") String forLanguage);

    @GET("api/v1.5/tr.json/detect")
    Observable<ProbableLanguage> getProbableLanguage(@Query("text") String text);

    @GET("api/v1.5/tr.json/translate")
    Observable<Translation> getTranslation(@Query("text") String textToTranslate,
                                           @Query("lang") String translationDirection);
}
