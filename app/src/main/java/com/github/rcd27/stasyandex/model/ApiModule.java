package com.github.rcd27.stasyandex.model;


import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import com.github.rcd27.stasyandex.model.dictionary.DictionaryAPI;
import com.github.rcd27.stasyandex.model.translation.TranslationAPI;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static okhttp3.logging.HttpLoggingInterceptor.Level;

public class ApiModule {
    public static TranslationAPI getTranslationApi() {
        OkHttpClient translationClient = getHttpClient(Const.TRANSLATE_API_KEY);
        Retrofit translationService = buildService(Const.TRANSLATE_URL, translationClient);
        return translationService.create(TranslationAPI.class);
    }


    public static DictionaryAPI getDictionaryApi() {
        OkHttpClient dictionaryClient = getHttpClient(Const.DICTIONARY_API_KEY);
        Retrofit dictionaryService = buildService(Const.DICTIONARY_URL, dictionaryClient);
        return dictionaryService.create(DictionaryAPI.class);
    }

    @NonNull
    private static OkHttpClient getHttpClient(String apiKey) {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient().newBuilder();
        HttpLoggingInterceptor networkLogger = new HttpLoggingInterceptor();
        networkLogger.setLevel(Level.BASIC);
        httpClientBuilder.addInterceptor(new CustomInterceptor(apiKey));
        httpClientBuilder.addInterceptor(networkLogger);
        return httpClientBuilder.build();
    }

    private static Retrofit buildService(String baseUrl, OkHttpClient client) {
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
        public Response intercept(Chain chain) throws IOException {
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
