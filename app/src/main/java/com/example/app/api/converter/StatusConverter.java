package com.example.app.api.converter;

import com.example.app.database.model.Status;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StatusConverter {
    @SerializedName("status")
    @Expose
    private List<Status> status;

    public StatusConverter() {
    }

    public List<Status> getStatus() {
        return status;
    }

    public void setStatus(List<Status> status) {
        this.status = status;
    }
}
