package com.example.app.database.dao;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import androidx.lifecycle.LiveData;

import com.example.app.database.model.Status;

import java.util.List;

@Dao
public interface StatusDAO {
    @Insert (onConflict = REPLACE)
    public void insertStatus(Status... status);
    @Update
    public void updateStatus(Status... status);
    @Delete
    public void deleteStatus(Status... status);
    // The conflict strategy defines what happens,
    // if there is an existing entry.
    // The default action is ABORT.

    @Query("SELECT * FROM kma_status")
    public LiveData<List<Status>> getAllStatus();

    @Query("SELECT * FROM kma_status where status_id = :id")
    public Status getStatusById(int id);

    @Query("DELETE FROM kma_status WHERE status_id = :id")
    void deleteStatusById(int id);

}
