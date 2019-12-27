package com.admin.model.menu;

public class SonMenu {

    private String path;
    private String component;
    private String name;
    private boolean hidden;

    public String getPath() {
        return path;
    }

    public boolean isHidden() {
        return hidden;
    }

    public SonMenu setHidden(boolean hidden) {
        this.hidden = hidden;
        return this;
    }

    public SonMenu setPath(String path) {
        this.path = path;
        return this;
    }

    public String getComponent() {
        return component;
    }

    public SonMenu setComponent(String component) {
        this.component = component;
        return this;
    }

    public String getName() {
        return name;
    }

    public SonMenu setName(String name) {
        this.name = name;
        return this;
    }

    public MenuData getMeta() {
        return meta;
    }

    public SonMenu setMeta(MenuData meta) {
        this.meta = meta;
        return this;
    }

    private MenuData meta;

}
