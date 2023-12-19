package com.example.app.database.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "kma_connection")
public class Connection {
    @PrimaryKey
    @NonNull
    @SerializedName("connection_id")
    @Expose
    private int connection_id;
    @SerializedName("task_id")
    @Expose
    private int task_id;
    @SerializedName("file_id")
    @Expose
    private String file_id;


    public Connection(@NonNull int connection_id, int task_id, String file_id) {
        this.connection_id = connection_id;
        this.task_id = task_id;
        this.file_id = file_id;
    }

    @NonNull
    public int getConnection_id() {
        return connection_id;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public String getFile_id() {
        return file_id;
    }

    public void setFile_id(String file_id) {
        this.file_id = file_id;
    }
}
