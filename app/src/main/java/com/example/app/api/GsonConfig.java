package com.example.app.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonConfig {
    public static final String DATE_PATTERN = "yyyy-MM-dd'T'hh:mm:ssZ";

    private static final Gson INSTANCE;

    static {
        INSTANCE = new GsonBuilder().setDateFormat(DATE_PATTERN).create();
    }
    public static Gson getGson() {
        return INSTANCE;
    }

}
