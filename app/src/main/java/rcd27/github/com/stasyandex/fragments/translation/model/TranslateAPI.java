package rcd27.github.com.stasyandex.fragments.translation.model;


import rcd27.github.com.stasyandex.fragments.translation.model.dto.AvailableLanguagesDTO;
import rcd27.github.com.stasyandex.fragments.translation.model.dto.ProbableLanguageDTO;
import rcd27.github.com.stasyandex.fragments.translation.model.dto.TranslationDTO;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface TranslateAPI {
    @GET("api/v1.5/tr.json/getLangs")
    Observable<AvailableLanguagesDTO> getAvailableLangs(@Query("ui") String forLanguage);

    @GET("api/v1.5/tr.json/detect")
    Observable<ProbableLanguageDTO> getProbableLanguage(@Query("word") String text);

    @GET("api/v1.5/tr.json/translate")
    Observable<TranslationDTO> getTranslation(@Query("word") String textToTranslate,
                                              @Query("lang") String translationDirection);
}