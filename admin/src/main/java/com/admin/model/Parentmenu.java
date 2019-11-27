package com.admin.model;

import javax.print.DocFlavor;
import java.util.List;

public class Parentmenu {

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

    public void setMeta(Menudata meta) {
        this.meta = meta;
    }

    public Menudata getMeta() {
        return meta;
    }

    public List<Submenu> getChildren() {
        return children;
    }

    public void setChildren(List<Submenu> children) {
        this.children = children;
    }



    private String name;
    private Menudata meta;

    private  List<Submenu> children;

}
