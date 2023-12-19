package com.example.app.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import androidx.lifecycle.LiveData;

import com.example.app.database.model.WorkItem;
import java.util.List;

@Dao
public interface WorkItemDAO {
    // methods without writing SQL statement
    // Room library does not understand the function names
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public long insertWorkItem(WorkItem item);
    // return an int value indicating the number of rows that were updated successfully
    @Update
    public void updateWorkItem(WorkItem... items);
    @Delete
    public void deleteWorkItem(WorkItem item);
    // writing SQL statements
    @Query("SELECT * FROM kma_work_item")
    public LiveData<List<WorkItem>> getAllWorkItems();

    @Query("SELECT * FROM kma_work_item WHERE work_id = :work_id")
    public WorkItem getWorkItemById(int work_id);
}
