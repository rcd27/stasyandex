package rcd27.github.com.stasyandex.model;


import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import rcd27.github.com.stasyandex.model.dictionary.DictionaryAPI;
import rcd27.github.com.stasyandex.model.translation.TranslationAPI;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static okhttp3.logging.HttpLoggingInterceptor.Level;

public class ApiModule {
    //TODO избавиться от этого позорного дублирования кода.
    public static TranslationAPI getTranslationApi() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient().newBuilder();
        /*OUT OF ORDER****/
        HttpLoggingInterceptor networkLogger = new HttpLoggingInterceptor();
        networkLogger.setLevel(Level.BASIC);
        /*---------------*/
        httpClientBuilder.addInterceptor(new CustomInterceptor(Const.TRANSLATE_API_KEY));
        httpClientBuilder.addInterceptor(networkLogger);

        return new Retrofit.Builder()
                .baseUrl(Const.TRANSLATE_URL)
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build().create(TranslationAPI.class);
    }

    public static DictionaryAPI getDictionaryApi() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient().newBuilder();
        httpClientBuilder.addInterceptor(new CustomInterceptor(Const.DICTIONARY_API_KEY));

        return new Retrofit.Builder()
                .baseUrl(Const.DICTIONARY_URL)
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build().create(DictionaryAPI.class);
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
