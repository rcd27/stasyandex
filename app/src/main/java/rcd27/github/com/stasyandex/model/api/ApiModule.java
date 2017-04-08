package rcd27.github.com.stasyandex.model.api;


import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiModule {
    private static final String TRANSLATE_URL = "https://translate.yandex.net/";
    private static final String TRANSLATE_API_KEY
            = "trnsl.1.1.20170329T205120Z.3a93ae67f64429e6" +
            ".6d650fe0829ed1f0a269d9e3f850da537b87cc4b";

    private static final String DICTIONARY_URL = "https://dictionary.yandex.net/";
    private static final String DICTIONARY_API_KEY = "dict.1.1.20170408T212234Z." +
            "478c26abd6ca258f.8ada3c887a5c6a9c35bcfe919dab24faf9ffb732";

    //TODO избавиться от этого позорного дублирования кода.
    public static TranslateAPI getTranslateApi() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient().newBuilder();
        httpClientBuilder.addInterceptor(new CustomInterceptor(TRANSLATE_API_KEY));

        return new Retrofit.Builder()
                .baseUrl(TRANSLATE_URL)
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build().create(TranslateAPI.class);
    }

    public static DictionaryAPI getDictionaryApi() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient().newBuilder();
        httpClientBuilder.addInterceptor(new CustomInterceptor(DICTIONARY_API_KEY));

        return new Retrofit.Builder()
                .baseUrl(DICTIONARY_URL)
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build().create(DictionaryAPI.class);
    }

    //FIXME что-то там с этим интерсептером - посмотреть. Кажется он @Deprecated
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
