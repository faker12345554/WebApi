package com.admin.model;

import java.util.List;

public class Submenu {

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

    public Menudata getMeta() {
        return meta;
    }

    public void setMeta(Menudata meta) {
        this.meta = meta;
    }

    public void setName(String name) {
        this.name = name;
    }



    private String name;
    private Menudata meta;

}
