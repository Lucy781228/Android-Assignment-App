package com.example.app.api.converter;

import com.example.app.database.model.Connection;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ConnectionConverter {
    @SerializedName("all_connections")
    @Expose
    private List<Connection> connections;

    public ConnectionConverter() {
    }

    public List<Connection> getConnections() {
        return connections;
    }

    public void setConnections(List<Connection> connections) {
        this.connections = connections;
    }
}
