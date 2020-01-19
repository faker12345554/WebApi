package com.admin.model.coordina;


import java.util.List;

public class MenuModel {
    private int id;
    private String Name;
    public int topid;
    public int getId() {
        return id;
    }

    public int getTopid() {
        return topid;
    }

    public List<MenuModel> getChildren() {
        return children;
    }

    public MenuModel setChildren(List<MenuModel> children) {
        this.children = children;
        return this;
    }

    public MenuModel setTopid(int topid) {
        this.topid = topid;
        return this;
    }

    public MenuModel setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return Name;
    }

    public MenuModel setName(String name) {
        Name = name;
        return this;
    }


    private List<MenuModel> children;


}
