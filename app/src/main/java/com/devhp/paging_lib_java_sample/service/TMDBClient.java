package com.devhp.paging_lib_java_sample.service;

import com.devhp.paging_lib_java_sample.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TMDBClient {
    private static Retrofit retrofit = null;
    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    public static TMDBService getService() {
        if (retrofit == null) {
            synchronized (TMDBClient.class) {
                HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                HttpLoggingInterceptor.Level level;
                if (BuildConfig.DEBUG) {
                    level = HttpLoggingInterceptor.Level.BODY;
                } else {
                    level = HttpLoggingInterceptor.Level.NONE;
                }
                logging.setLevel(level);
                OkHttpClient client = new OkHttpClient.Builder().addInterceptor(logging)
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .readTimeout(30, TimeUnit.SECONDS)
                        .writeTimeout(30, TimeUnit.SECONDS)
                        .build();
                retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(client).build();
            }
        }
        return retrofit.create(TMDBService.class);
    }

}
