package com.example.app.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static Retrofit retrofit = null;
    private static String BASE_URL = "http://192.168.1.144:8080/apps/kmaassignwork/";

    //singleton pattern
    public static AndroidNCApi getService() {

        // instance create for the first time
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory
                            (GsonConverterFactory.create())
                    .build();
        }
        // already created instance .
        return retrofit.create(AndroidNCApi.class);

    }
}