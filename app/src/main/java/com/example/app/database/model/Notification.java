package com.example.app.database.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

@Entity(tableName = "kma_notification")
public class Notification {
    @PrimaryKey
    @NonNull
    @SerializedName("notification_id")
    @Expose
    private int notification_id;
    @SerializedName("user_create")
    @Expose
    private String user_create;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("time_create")
    @Expose
    private Date time_create;
    @SerializedName("is_read")
    @Expose
    private Boolean is_read;
    @SerializedName("user_received")
    @Expose
    private String user_received;

    public Notification(@NonNull int notification_id, String user_create, String content, Date time_create, Boolean is_read, String user_received) {
        this.notification_id = notification_id;
        this.user_create = user_create;
        this.content = content;
        this.time_create = time_create;
        this.is_read = is_read;
        this.user_received = user_received;
    }

    @NonNull
    public int getNotification_id() {
        return notification_id;
    }

    public String getUser_create() {
        return user_create;
    }

    public String getContent() {
        return content;
    }

    public Date getTime_create() {
        return time_create;
    }

    public Boolean getIs_read() {
        return is_read;
    }

    public String getUser_received() {
        return user_received;
    }

    public void setUser_create(String user_create) {
        this.user_create = user_create;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTime_create(Date time_create) {
        this.time_create = time_create;
    }

    public void setIs_read(Boolean is_read) {
        this.is_read = is_read;
    }

    public void setUser_received(String user_received) {
        this.user_received = user_received;
    }
}
