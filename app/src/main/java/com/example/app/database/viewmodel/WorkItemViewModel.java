package com.example.app.database.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.app.database.AndroidNCDatabase;
import com.example.app.database.dao.WorkItemDAO;
import com.example.app.database.model.WorkItem;

import java.util.List;

public class WorkItemViewModel extends AndroidViewModel {
    private WorkItemDAO workItemDAO;
    private LiveData<List<WorkItem>> allWorkItems;

    public WorkItemViewModel(@NonNull Application application) {
        super(application);
        workItemDAO = AndroidNCDatabase.getDatabase(application).workItemDAO();
        allWorkItems = workItemDAO.getAllWorkItems();
    }

    public LiveData<List<WorkItem>> getAllWorkItems() {
        return allWorkItems;
    }
}

