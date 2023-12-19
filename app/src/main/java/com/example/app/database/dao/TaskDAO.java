package com.example.app.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.app.database.model.Task;

import java.util.List;

@Dao
public interface TaskDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insertTask(Task... tasks);
    @Update
    public void updateTask(Task... tasks);
    @Delete
    public void deleteTask(Task... tasks);

    @Query("SELECT * FROM kma_task_item")
    public List<Task> getAllTasks();

    @Query("SELECT * FROM kma_task_item where task_id = :id")
    public Task getTaskById(int id);

    @Query("SELECT * FROM kma_task_item WHERE work_id = :id")
    public List<Task> getTaskByWorkId(int id);

    @Query("DELETE FROM kma_task_item WHERE task_id = :id")
    void deleteTaskById(int id);

    @Query("DELETE FROM kma_task_item WHERE work_id = :id")
    void deleteTaskByWordId(int id);
}
