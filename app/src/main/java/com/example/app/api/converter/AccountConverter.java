package com.example.app.api.converter;

import com.example.app.database.model.Account;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class AccountConverter {
    @SerializedName("users")
    @Expose
    private Map<String, Account> users;

    public AccountConverter() {
    }

    public Map<String, Account> getUsers() {
        return users;
    }

    public void setUsers(Map<String, Account> users) {
        this.users = users;
    }
}
