package com.example.app.api;


import android.content.Context;

import com.example.app.api.converter.AccountConverter;
import com.example.app.api.converter.WorkItemConverter;
import com.example.app.database.model.Account;
import com.example.app.database.model.Comment;
import com.example.app.database.model.Connection;
import com.example.app.database.model.Level;
import com.example.app.database.model.Notification;
import com.example.app.database.model.Status;
import com.example.app.database.model.Task;
import com.example.app.database.model.TaskFile;
import com.example.app.database.model.WorkItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AndroidNCApi {
    // Comment
    @GET("kma_comments")
    Call<List<Comment>> getKmaCommentInTask(@Query("task_id") int task_id);
    @POST("create_kma_comment")
    Call<Comment> createKmaComment(@Body Comment comment);
    @DELETE("delete_comment")
    Call<Void> deleteComment(@Query("comment_id") int comment_id);

    // Connection
    @GET("kma_connection")
    Call<List<Connection>> getKmaConnectionByTask(@Query("task_id") int task_id);
    @POST("create_kma_connection")
    Call<Connection> createKmaConnection(@Body Connection connection);
    @DELETE("delete_connection")
    Call<Void> deleteConnection(@Query("connection_id") int connection_id);


    // Level
    @GET("all_kma_level")
    Call<List<Level>> getAllKmaLevel();

    @POST("create_kma_level")
    Call<Level> createKmaLevel(@Body Level level);

    // Status
    @GET("all_kma_status")
    Call<List<Status>> getAllKmaStatus();
    @POST("create_kma_level")
    Call<Status> createKmaStatus(@Body Status status);

    // Task
    @GET("kma_tasks")
    Call<List<Task>> getTaskByWork(@Query("work_id") int work_id);
    @GET("kma_tasks")
    Call<List<Task>> getTaskByUserCreate(@Query("user_id") int user_create);
    @GET("kma_tasks")
    Call<List<Task>> getTaskByUserSupport(@Query("user_id") int user_support);
    @GET("kma_tasks")
    Call<List<Task>> getTaskByUserRespond(@Query("user_id") int user_respond);
    @POST("create_kma_task")
    Call<Task> createKmaTask(@Body Task task);
    @DELETE("delete_kma_task")
    Call<Void> deleteKmaTask(@Query("task_id") int task_id);


    // WorkItem
    @GET("kma_work")
    public Call<WorkItem> getKmaWork(@Query("user_id") int user_create);
    @POST("create_kma_work")
    Call<Task> createKmaWork(@Body WorkItem work);
    @DELETE("delete_kma_work")
    Call<Void> deleteKmaWork(@Query("work_id") int work_id);

    //Account
    @GET("accounts")
    Call<List<Account>> getAllAccount();

    // TaskFile
    @GET("filecache")
    Call<List<TaskFile>> getAllFiles();

    // Notification
    Call<List<Notification>> getNotif();
}