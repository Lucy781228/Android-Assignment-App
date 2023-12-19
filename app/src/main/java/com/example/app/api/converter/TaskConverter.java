package com.example.app.api.converter;

import com.example.app.database.model.Task;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TaskConverter {
    @SerializedName("tasks")
    @Expose
    private List<Task> tasks;

    public TaskConverter() {
    }
    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
