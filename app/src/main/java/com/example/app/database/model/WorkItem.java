package com.example.app.database.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "kma_work_item")
public class WorkItem {
    @PrimaryKey(autoGenerate = true)
    private int work_id;
    private String work_name;
    private String work_description;
    private int level_id;
    private int status_id;
    private String user_create;

    public WorkItem(String work_name, String work_description, int level_id,
                    int status_id, String user_create) {
        this.work_name = work_name;
        this.work_description = work_description;
        this.level_id = level_id;
        this.status_id = status_id;
        this.user_create = user_create;
    }

    public void setWork_id(int work_id) {
        this.work_id = work_id;
    }

    public int getWork_id() {
        return work_id;
    }

    public String getUser_create() {
        return user_create;
    }

    public String getWork_name() {
        return work_name;
    }

    public void setWork_name(String work_name) {
        this.work_name = work_name;
    }

    public String getWork_description() {
        return work_description;
    }

    public void setWork_description(String work_description) {
        this.work_description = work_description;
    }

    public int getLevel_id() {
        return level_id;
    }

    public void setLevel_id(int level_id) {
        this.level_id = level_id;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public void setUser_create(String user_create) {
        this.user_create = user_create;
    }

}