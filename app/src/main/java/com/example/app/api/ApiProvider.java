package com.example.app.api;

import android.content.Context;
import com.example.app.R;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiProvider {
    private static Retrofit retrofit = null;

    public static Retrofit getClient(Context context, String username, String password) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();

        // Add the Authorization header
        String credentials = username + ":" + password;
        String base64Credentials = android.util.Base64.encodeToString(credentials.getBytes(), android.util.Base64.NO_WRAP);
        clientBuilder.addInterceptor(chain -> {
            okhttp3.Request original = chain.request();
            okhttp3.Request.Builder requestBuilder = original.newBuilder()
                    .header("Authorization", "Basic " + base64Credentials)
                    .header("OCS-APIRequest", "true"); // Add other headers if needed

            okhttp3.Request request = requestBuilder.build();
            return chain.proceed(request);
        });

        OkHttpClient client = clientBuilder.build();

        retrofit = new Retrofit.Builder()
                .baseUrl(context.getString(R.string.url_server))
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }
}

