package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import com.example.app.databinding.ActivityAuthenticatorBinding;
import com.example.app.ui.activities.LoginActivity;


import java.util.regex.Pattern;

public class AuthenticatorActivity extends AppCompatActivity {
    private ActivityAuthenticatorBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAuthenticatorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //on text change
        binding.severEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                binding.serverTil.setHelperText("");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.serverTil.setHelperText("");
            }

            @Override
            public void afterTextChanged(Editable s) {
                binding.serverTil.setHelperText("");
            }
        });

        binding.serverTil.setEndIconOnClickListener(v -> {
            if (validateLink()) {
                checkConnectToSever();
            }
            Intent intent = new Intent(AuthenticatorActivity.this, LoginActivity.class);
            startActivity(intent);
        });

    }

    private boolean validateLink() {

        String text = binding.severEdt.getText().toString().trim();
        if (text.isEmpty()) {
            binding.serverTil.setHelperText("Please enter your server");
            binding.serverTil.setHelperTextColor(ColorStateList.valueOf(Color.RED));
            return false;
        }
        return true;

    }

    private void checkConnectToSever() {

        String text = binding.severEdt.getText().toString().trim();

        // Todo after validate link save link to shared preferences

    }


}