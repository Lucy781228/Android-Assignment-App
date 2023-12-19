package com.example.app.api.converter;

import com.example.app.database.model.Level;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LevelConverter {
    @SerializedName("levels")
    @Expose
    private List<Level> levels;

    public LevelConverter() {
    }

    public List<Level> getLevels() {
        return levels;
    }

    public void setLevels(List<Level> levels) {
        this.levels = levels;
    }
}
