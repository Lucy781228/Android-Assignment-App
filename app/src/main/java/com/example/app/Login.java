package com.example.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app.api.AndroidNCApi;
import com.example.app.api.ApiProvider;
import com.example.app.api.RetrofitInstance;
import com.example.app.api.converter.AccountConverter;
import com.example.app.database.model.Account;
import com.owncloud.android.lib.common.OwnCloudClient;
import com.owncloud.android.lib.common.OwnCloudClientFactory;
import com.owncloud.android.lib.common.OwnCloudCredentialsFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {
    private OwnCloudClient mClient;
    ArrayList<Account> accounts = new ArrayList<>();
    EditText edServer, edUsername, edPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edServer = findViewById(R.id.edServer);
        edUsername = findViewById(R.id.edUsername);
        edPassword = findViewById(R.id.edPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String server = edServer.getText().toString();
//                String username = edUsername.getText().toString();
//                String password = edPassword.getText().toString();
                String server = "http://192.168.1.144:8080";
                String username = "admin";
                String password = "admin";

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        getUsers(username);
                    }
                }).start();

                Intent intent = new Intent(Login.this, MainActivity2.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });
    }

    void getUsers(String username) {
        Retrofit retrofit = ApiProvider.getClient(this, "admin", "admin");
        AndroidNCApi api = retrofit.create(AndroidNCApi.class);
        Call<List<Account>> call = api.getAllAccount();

        call.enqueue(new Callback<List<Account>>() {
            @Override
            public void onResponse(Call<List<Account>> call, Response<List<Account>> response) {
                if (response.isSuccessful()) {
                    List<Account> accounts = response.body();
                    if (accounts != null && !accounts.isEmpty()) {
                        // Iterate over the list of accounts
                        for (Account account : accounts) {
                            String userId = account.getAccount_id();
                            Log.e("ACCOUNT", "User ID: " + userId);
                        }
                    } else {
                        Log.e("ACCOUNT", "Empty or null list of accounts");
                    }
                } else {
                    Log.e("ACCOUNT", "Unsuccessful response: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Account>> call, Throwable t) {
                Log.e("ACCOUNT", "FAILURE", t);
            }
        });
    }


}
