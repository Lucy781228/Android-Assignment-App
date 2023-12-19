package com.example.app.database.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity(tableName = "kma_status")
public class Status {
    @PrimaryKey(autoGenerate = true)
    private int status_id;
    private String status_name;

    public Status(String status_name) {
        this.status_name = status_name;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public int getStatus_id() {
        return status_id;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }
}
