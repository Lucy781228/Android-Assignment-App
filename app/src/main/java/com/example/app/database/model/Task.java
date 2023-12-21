package com.example.app.database.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "kma_task_item")
public class Task {
    @PrimaryKey(autoGenerate = true)
    private int task_id;
    private String task_name;
    private String task_description;
    private int status_id;
    private int work_id;
    private int level_id;
    private Date task_start;
    private Date task_end;
    private String user_create;
    private String user_respond;
    private String user_support;

    public Task(String task_name, String task_description,
                int status_id, int work_id, int level_id, Date task_start,
                Date task_end, String user_create, String user_respond, String user_support) {
        this.task_name = task_name;
        this.task_description = task_description;
        this.status_id = status_id;
        this.work_id = work_id;
        this.level_id = level_id;
        this.task_start = task_start;
        this.task_end = task_end;
        this.user_create = user_create;
        this.user_respond = user_respond;
        this.user_support = user_support;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public int getTask_id() {
        return task_id;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getTask_description() {
        return task_description;
    }

    public void setTask_description(String task_description) {
        this.task_description = task_description;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public int getWork_id() {
        return work_id;
    }

    public void setWork_id(int work_id) {
        this.work_id = work_id;
    }

    public int getLevel_id() {
        return level_id;
    }

    public Date getTask_start() {
        return task_start;
    }

    public Date getTask_end() {
        return task_end;
    }

    public String getUser_create() {
        return user_create;
    }

    public String getUser_respond() {
        return user_respond;
    }

    public String getUser_support() {
        return user_support;
    }

    public void setLevel_id(int level_id) {
        this.level_id = level_id;
    }

    public void setTask_start(Date task_start) {
        this.task_start = task_start;
    }

    public void setTask_end(Date task_end) {
        this.task_end = task_end;
    }

    public void setUser_create(String user_create) {
        this.user_create = user_create;
    }

    public void setUser_respond(String user_respond) {
        this.user_respond = user_respond;
    }

    public void setUser_support(String user_support) {
        this.user_support = user_support;
    }
    public String getLevel() {
        if(this.level_id == 1) return "A";
        else if(this.level_id == 2) return "B";
        else return "C";
    }
}
