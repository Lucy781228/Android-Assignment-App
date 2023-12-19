package com.example.app.database.dao;

import androidx.room.Dao;
import androidx.room.Query;

import androidx.lifecycle.LiveData;

import com.example.app.database.model.Account;

import java.util.List;
@Dao
public interface AccountDAO {
    @Query("SELECT * FROM accounts")
    public LiveData<List<Account>> getAllAccounts();
}
