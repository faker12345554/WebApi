package com.admin.model;

import java.util.List;

public class ParentMenu {

    private String path;
    private String component;
    private String redirect;

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

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMeta(MenuData meta) {
        this.meta = meta;
    }

    public MenuData getMeta() {
        return meta;
    }

    public List<SonMenu> getChildren() {
        return children;
    }

    public void setChildren(List<SonMenu> children) {
        this.children = children;
    }



    private String name;
    private MenuData meta;

    private  List<SonMenu> children;

}
