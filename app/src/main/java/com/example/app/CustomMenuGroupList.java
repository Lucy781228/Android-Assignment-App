package com.example.app;

import java.util.List;

public class CustomMenuGroupList {
    private String name;
    private int iconResourceId;
    private List<CustomMenuItem> children;

    public CustomMenuGroupList(String name, int iconResourceId, List<CustomMenuItem> children) {
        this.name = name;
        this.iconResourceId = iconResourceId;
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public int getIconResourceId() {
        return iconResourceId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIconResourceId(int iconResourceId) {
        this.iconResourceId = iconResourceId;
    }

    public List<CustomMenuItem> getChildren() {
        return children;
    }
}
