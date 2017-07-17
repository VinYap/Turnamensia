package com.tugasakhir.turnamensiamember.Model.API;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Asus on 12/07/2017.
 */

public class ConnectionAPI {
    private static final String BASE_URL = "http://192.168.1.100";
    private static ConnectionAPI instance;
    private static APIModel api;
    private static Retrofit retrofit;

    /**
     * Instances / Singleton
     *
     * @return Object ConnectionAPI
     */
    public static ConnectionAPI getInstance() {
        return (instance == null) ? new ConnectionAPI() : instance;
    }

    /**
     * Get all Routing From TourneyAPIModel
     *
     * @return Object APIModel
     */
    public APIModel getAPIModel() {
        return api;
    }

    /**
     * Build : Retrofit
     * Create : TourneyAPIModel
     */
    private ConnectionAPI() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("X-Requested-With", "XMLHttpRequest")
                        .header("Accept", "application/json")
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });
        httpClient.readTimeout(10, TimeUnit.SECONDS);
        httpClient.connectTimeout(10, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        api = retrofit.create(APIModel.class);
    }

    public static String getBaseUrl() {
        return BASE_URL;
    }
}
