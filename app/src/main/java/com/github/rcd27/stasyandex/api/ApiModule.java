package com.github.rcd27.stasyandex.api;

import android.support.annotation.NonNull;

import com.github.rcd27.stasyandex.common.Const;
import com.github.rcd27.stasyandex.dictionary.DictionaryContract;
import com.github.rcd27.stasyandex.translation.TranslationContract;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

@Module
public class ApiModule {

    @Provides
    @Singleton
    TranslationContract.Api provideTranslationApi() {
        return getTranslationApi();
    }

    @Provides
    @Singleton
    DictionaryContract.Api provideDictionaryApi() {
        return getDictionaryApi();
    }

    private TranslationContract.Api getTranslationApi() {
        OkHttpClient translationClient = getHttpClient(Const.TRANSLATE_API_KEY);
        Retrofit translationService = buildService(Const.TRANSLATE_URL, translationClient);
        return translationService.create(TranslationContract.Api.class);
    }


    private DictionaryContract.Api getDictionaryApi() {
        OkHttpClient dictionaryClient = getHttpClient(Const.DICTIONARY_API_KEY);
        Retrofit dictionaryService = buildService(Const.DICTIONARY_URL, dictionaryClient);
        return dictionaryService.create(DictionaryContract.Api.class);
    }

    private OkHttpClient getHttpClient(@NonNull String apiKey) {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient().newBuilder();
        HttpLoggingInterceptor networkLogger = new HttpLoggingInterceptor(Timber::d);
        networkLogger.setLevel(HttpLoggingInterceptor.Level.BASIC);
        httpClientBuilder.addInterceptor(new CustomInterceptor(apiKey));
        httpClientBuilder.addInterceptor(networkLogger);
        return httpClientBuilder.build();
    }

    private Retrofit buildService(String baseUrl, OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                //TODO вот тут надо вшить обработку ошибки 400
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    private static class CustomInterceptor implements Interceptor {
        private final String apiKey;

        CustomInterceptor(String apiKey) {
            this.apiKey = apiKey;
        }

        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            Request original = chain.request();
            HttpUrl originalHttpUrl = original.url();

            HttpUrl resultUrl = originalHttpUrl.newBuilder()
                    .addQueryParameter("key", apiKey)
                    .build();

            Request.Builder requestBuilder = original.newBuilder()
                    .url(resultUrl);

            Request request = requestBuilder.build();
            return chain.proceed(request);
        }
    }
}
