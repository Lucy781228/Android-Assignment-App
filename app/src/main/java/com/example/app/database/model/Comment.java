package com.example.app.database.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "kma_comment")
public class Comment {
    @PrimaryKey
    @SerializedName("comment_id")
    @Expose
    private int comment_id;
    @SerializedName("task_id")
    @Expose
    private int task_id;
    @SerializedName("user_create")
    @Expose
    private String user_create;
    @SerializedName("message")
    @Expose
    private String message;

    public Comment(int comment_id, int task_id, String user_create, String message) {
        this.comment_id = comment_id;
        this.task_id = task_id;
        this.user_create = user_create;
        this.message = message;
    }

    public int getComment_id() {
        return comment_id;
    }

    public String getUser_create() {
        return user_create;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTask_id() {
        return task_id;
    }

}
