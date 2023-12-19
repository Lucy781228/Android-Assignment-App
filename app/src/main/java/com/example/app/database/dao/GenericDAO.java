package com.example.app.database.dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import java.util.List;
import java.util.Optional;

public interface GenericDAO<T> {

    List<T> getAll();

    Optional<T> get(String id);

    @SuppressWarnings("unchecked")
    @Insert
    void insert(T t);

    @SuppressWarnings("unchecked")
    @Update
    void update(T t);

    @SuppressWarnings("unchecked")
    @Delete
    void delete(T t);
}
