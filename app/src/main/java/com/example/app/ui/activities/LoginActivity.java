package com.example.app.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app.api.AndroidNCApi;
import com.example.app.api.ApiProvider;
import com.example.app.database.model.Account;
import com.example.app.databinding.ActivityLoginBinding;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnLogin.setOnClickListener(v -> {
            String server = "http://192.168.1.144:8080";
            String username = "admin";
            String password = "admin";

            new Thread(() -> getUsers(username)).start();

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        });
    }

    private void getUsers(String username) {
        Retrofit retrofit = ApiProvider.getClient(this, "admin", "admin");
        AndroidNCApi api = retrofit.create(AndroidNCApi.class);
        Call<List<Account>> call = api.getAllAccount();

        call.enqueue(new Callback<List<Account>>() {
            @Override
            public void onResponse(@NotNull Call<List<Account>> call, @NotNull Response<List<Account>> response) {
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
            public void onFailure(@NotNull Call<List<Account>> call, @NotNull Throwable t) {
                Log.e("ACCOUNT", "FAILURE", t);
            }
        });
    }

}
