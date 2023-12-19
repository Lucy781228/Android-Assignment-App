package com.example.app.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.app.database.model.Notification;

@Dao
public interface NotificationDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insertNotif(Notification... notifications);
    @Delete
    public void deleteNotif(Notification... notifications);

    @Query("SELECT * FROM kma_notification WHERE user_received = :user_id")
    public Notification getNotif(String user_id);
}
