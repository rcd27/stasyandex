package rcd27.github.com.stasyandex.model.api;


import rcd27.github.com.stasyandex.model.dto.AvailableLanguagesDTO;
import rcd27.github.com.stasyandex.model.dto.ProbableLanguageDTO;
import rcd27.github.com.stasyandex.model.dto.TranslationDTO;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiInterface {
    @GET("api/v1.5/tr.json/getLangs")
    Observable<AvailableLanguagesDTO> getAvailableLangs(@Query("ui") String forLanguage);

    @GET("api/v1.5/tr.json/detect")
    Observable<ProbableLanguageDTO> getProbableLanguage(@Query("text") String text);

    @GET("api/v1.5/tr.json/translate")
    Observable<TranslationDTO> getTranslation(@Query("text") String textToTranslate,
                                              @Query("lang") String translationDirection);
}
