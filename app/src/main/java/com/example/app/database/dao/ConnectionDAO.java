package com.example.app.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.example.app.database.model.Connection;

import java.util.List;
@Dao
public interface ConnectionDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insertConnection(Connection... connections);
    @Update
    public void updateConnection(Connection... connections);
    @Delete
    public void deleteConnection(Connection... connections);
//    @Query("SELECT * FROM kma_connection")
//    public LiveData<List<Connection>> getAllKmaConnections();

    @Query("SELECT * FROM kma_connection WHERE task_id = :task_id")
    public Connection getKmaConnectionByTask(int task_id);

    @Query("DELETE FROM kma_connection WHERE connection_id = :id")
    void deleteConnection(int id);
}
