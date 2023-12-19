package com.example.app.database.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.lifecycle.LiveData;
import com.example.app.database.model.TaskFile;

import java.util.List;

@Dao
public interface TaskFileDAO {
    @Query("SELECT * FROM kma_task_file")
    public LiveData<List<TaskFile>> getAllFiles();
}
