package com.example.app.database.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "accounts")
public class Account {
    @PrimaryKey
    @NonNull
    @SerializedName("account_id")
    @Expose
    private String account_id;
    @SerializedName("display_name")
    @Expose
    private String display_name;

    public Account(@NonNull String account_id, String display_name) {
        this.account_id = account_id;
        this.display_name = display_name;
    }

    @NonNull
    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(@NonNull String account_id) {
        this.account_id = account_id;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }
}
