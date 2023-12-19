package com.example.app.database.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity(tableName = "kma_level")
public class Level {
    @PrimaryKey(autoGenerate = true)
    private int level_id;
    private String level_name;
    private String level_description;

    public Level(String level_name, String level_description) {
        this.level_name = level_name;
        this.level_description = level_description;
    }

    @NonNull
    public int getLevel_id() {
        return level_id;
    }

    public void setLevel_name(String level_name) {
        this.level_name = level_name;
    }

    public void setLevel_description(String level_description) {
        this.level_description = level_description;
    }

    public void setLevel_id(int level_id) {
        this.level_id = level_id;
    }

    public String getLevel_name() {
        return level_name;
    }

    public String getLevel_description() {
        return level_description;
    }

    public void remove(Level existLevel) {
        // Your remove method implementation here
    }
}