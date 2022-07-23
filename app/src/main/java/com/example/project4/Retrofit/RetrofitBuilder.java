package com.example.project4.Retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {
    private static Retrofit instance;

    private RetrofitBuilder() {
    }


    public static Retrofit getInstance() {
        if (instance == null) {
            synchronized (RetrofitBuilder.class) {
                if (instance == null) {
//                    builder pattern changing of parameters
                    instance = new Retrofit.Builder().baseUrl("https://www.omdbapi.com/")
                            .addConverterFactory(GsonConverterFactory.create()).client(new OkHttpClient()).build();

                }
            }
        }
        return instance;

    }
}
