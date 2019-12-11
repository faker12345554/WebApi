package com.admin.model.menu;

public class SonMenu {

    private String path;
    private String component;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getName() {
        return name;
    }

    public MenuData getMeta() {
        return meta;
    }

    public void setMeta(MenuData meta) {
        this.meta = meta;
    }

    public void setName(String name) {
        this.name = name;
    }



    private String name;
    private MenuData meta;

}
