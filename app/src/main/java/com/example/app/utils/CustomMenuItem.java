package com.example.app.utils;

public class CustomMenuItem {
    private String name;
    private int workId;
    private int iconResourceId;
    private int levelId;

    public CustomMenuItem(String name, int workId, int iconResourceId, int levelId) {
        this.name = name;
        this.workId = workId;
        this.iconResourceId = iconResourceId;
        this.levelId = levelId;
    }

    public String getName() {
        return name;
    }

    public int getWorkId() {
        return workId;
    }

    public int getIconResourceId() {
        return iconResourceId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWorkId(int workId) {
        this.workId = workId;
    }

    public void setIconResourceId(int iconResourceId) {
        this.iconResourceId = iconResourceId;
    }

    public boolean shouldShowDetailIcon() {
        return true;
    }

    public int getLevelId() {
        return levelId;
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }
}