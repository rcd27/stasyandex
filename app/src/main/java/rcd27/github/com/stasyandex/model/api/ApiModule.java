package rcd27.github.com.stasyandex.model.api;


import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiModule {
    private static final String API_KEY
            = "trnsl.1.1.20170329T205120Z.3a93ae67f64429e6" +
            ".6d650fe0829ed1f0a269d9e3f850da537b87cc4b";
    private static final String BASE_URL = "https://translate.yandex.net/";

    public static ApiInterface getApiInterface() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient().newBuilder();
        httpClientBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                HttpUrl originalHttpUrl = original.url();

                HttpUrl resultUrl = originalHttpUrl.newBuilder()
                        .addQueryParameter("key", API_KEY)
                        .build();

                Request.Builder requestBuilder = original.newBuilder()
                        .url(resultUrl);

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiInterface.class);
    }
}
