package com.example.app.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import androidx.lifecycle.LiveData;

import com.example.app.database.model.Level;

import java.util.List;

@Dao
public interface LevelDAO{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insertLevel(Level... levels);
    @Update
    public void updateLevel(Level... levels);
    @Delete
    public void deleteLevel(Level... levels);

    @Query("SELECT * FROM kma_level")
    public LiveData<List<Level>> getAllLevels();

    @Query("SELECT * FROM kma_level where level_id = :id")
    public Level getLevelById(int id);

    @Query("DELETE FROM kma_level WHERE level_id = :id")
    void deleteLevelById(int id);
}
