package com.example.app.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import android.content.Context;

import com.example.app.database.convert.TaskConverter;
import com.example.app.database.dao.AccountDAO;
import com.example.app.database.dao.CommentDAO;
import com.example.app.database.dao.ConnectionDAO;
import com.example.app.database.dao.LevelDAO;
import com.example.app.database.dao.StatusDAO;
import com.example.app.database.dao.TaskDAO;
import com.example.app.database.dao.WorkItemDAO;
import com.example.app.database.model.Account;
import com.example.app.database.model.Comment;
import com.example.app.database.model.Connection;
import com.example.app.database.model.Level;
import com.example.app.database.model.Status;
import com.example.app.database.model.Task;
import com.example.app.database.model.WorkItem;

// bump version number if schema changes
@Database(
        entities = {
                WorkItem.class,
                Task.class,
                Level.class,
                Status.class,
                Account.class,
                Comment.class,
                Connection.class
        },
        exportSchema = false,
        version = 1)

@TypeConverters(TaskConverter.class)

public abstract class AndroidNCDatabase extends RoomDatabase {
    // Database name to be used
    private static final String ANDROID_NC_DB_NAME = "NC_ANDROID_DB.db";
    private static volatile AndroidNCDatabase INSTANCE;

    // Declare data access objects as abstract
    public abstract WorkItemDAO workItemDAO();
    public abstract TaskDAO taskDAO();
    public abstract LevelDAO levelDAO();
    public abstract StatusDAO statusDAO();
    public abstract AccountDAO accountDAO();
    public abstract TaskConverter taskConverter();
    public abstract CommentDAO commentDAO();
    public abstract ConnectionDAO connectionDAO();


    public static AndroidNCDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AndroidNCDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AndroidNCDatabase.class, ANDROID_NC_DB_NAME)
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    public void destroyDatabase() {
        INSTANCE = null;
    }

}
