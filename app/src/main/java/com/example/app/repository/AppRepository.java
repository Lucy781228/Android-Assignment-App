package com.example.app.repository;

import android.content.Context;

import com.example.app.api.AndroidNCApi;
import com.example.app.api.ApiProvider;
import com.example.app.database.AndroidNCDatabase;
import com.example.app.database.model.Task;
import com.example.app.database.model.WorkItem;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppRepository {

//    private AndroidNCApi api;
//    private AndroidNCDatabase database;
//    private Executor executor;  // Ensure you have the executor set up
//
//    public AppRepository(Context context, String username, String password) {
//        api = ApiProvider.getClient(context, username, password).create(AndroidNCApi.class);
//        database = AndroidNCDatabase.getDatabase(context);
//        executor = Executors.newSingleThreadExecutor();
//    }
//
//    public void getKmaWork(int userId) {
//        api.getKmaWork(userId).enqueue(new Callback<List<WorkItem>>() {
//            @Override
//            public void onResponse(Call<List<WorkItem>> call, Response<List<WorkItem>> response) {
//                if (response.isSuccessful()) {
//                    List<WorkItem> workItems = response.body();
//                    if (workItems != null && !workItems.isEmpty()) {
//                        // Update Room database with work
//                        executor.execute(() -> database.workItemDAO().deleteAllWorkItem());
//                        for (WorkItem workItem: workItems) {
//                            executor.execute(() -> database.workItemDAO().insertWorkItem(workItem));
//                        }
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<WorkItem>> call, Throwable t) {
//                // Handle failure
//            }
//        });
//    }
//
//    public void createKmaWork(WorkItem workItem) {
//        api.createKmaWork(workItem).enqueue(new Callback<Task>() {
//            @Override
//            public void onResponse(Call<Task> call, Response<Task> response) {
//                if (response.isSuccessful()) {
//                    // Handle successful creation
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Task> call, Throwable t) {
//                // Handle failure
//            }
//        });
//    }
//
//    public void deleteKmaWork(int workId) {
//        api.deleteKmaWork(workId).enqueue(new Callback<Void>() {
//            @Override
//            public void onResponse(Call<Void> call, Response<Void> response) {
//                if (response.isSuccessful()) {
//                    // Handle successful deletion
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Void> call, Throwable t) {
//                // Handle failure
//            }
//        });
//    }
//
//    // Similar methods for other database updates...

}
