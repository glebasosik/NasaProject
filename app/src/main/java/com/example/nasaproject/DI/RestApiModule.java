package com.example.nasaproject.DI;

import com.example.nasaproject.API.NasaApiService;

import org.jetbrains.annotations.NotNull;

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
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RestApiModule {

    public static String KEY = "gV7Oc4dHj4cFj53mKgSr7KrB1xlrZZTNJQbLs9cD";

    @Singleton
    @Provides
    public HttpLoggingInterceptor httpLoggingInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Singleton
    @Provides
    public Interceptor interceptor() {
        Interceptor interceptor = new Interceptor() {
            @NotNull
            @Override
            public Response intercept(@NotNull Chain chain) throws IOException {
                final Request original = chain.request();
                final HttpUrl originalHttpUrl = original.url();
                final HttpUrl url = originalHttpUrl.newBuilder()
                        .addQueryParameter("api_key", KEY)
                        .build();
                final Request.Builder requestBuilder = original.newBuilder()
                        .url(url);
                final Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };

        return interceptor;
    }

    @Singleton
    @Provides
    public OkHttpClient okHttpClient(HttpLoggingInterceptor httpLoggingInterceptor, Interceptor interceptor) {
        return new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).addInterceptor(interceptor).
                build();
    }

    @Singleton
    @Provides
    public Retrofit retrofit(OkHttpClient client) {
        return new Retrofit.Builder().client(client).addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://epic.gsfc.nasa.gov/api/")
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    public NasaApiService nasaApiService(Retrofit retrofit) {
        return retrofit.create(NasaApiService.class);
    }
}
